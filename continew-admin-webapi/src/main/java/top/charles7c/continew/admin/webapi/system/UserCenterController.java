/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.charles7c.continew.admin.webapi.system;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.xkcoding.justauth.AuthRequestFactory;

import cn.hutool.core.util.ReUtil;

import top.charles7c.continew.admin.common.constant.CacheConstants;
import top.charles7c.continew.admin.common.constant.RegexConstants;
import top.charles7c.continew.admin.common.enums.SocialSourceEnum;
import top.charles7c.continew.admin.common.util.SecureUtils;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.system.model.entity.UserSocialDO;
import top.charles7c.continew.admin.system.model.req.UserBasicInfoUpdateReq;
import top.charles7c.continew.admin.system.model.req.UserEmailUpdateRequest;
import top.charles7c.continew.admin.system.model.req.UserPasswordUpdateReq;
import top.charles7c.continew.admin.system.model.req.UserPhoneUpdateReq;
import top.charles7c.continew.admin.system.model.resp.AvatarResp;
import top.charles7c.continew.admin.system.model.resp.UserSocialBindResp;
import top.charles7c.continew.admin.system.service.UserService;
import top.charles7c.continew.admin.system.service.UserSocialService;
import top.charles7c.continew.starter.cache.redisson.util.RedisUtils;
import top.charles7c.continew.starter.core.util.ExceptionUtils;
import top.charles7c.continew.starter.core.util.validate.ValidationUtils;
import top.charles7c.continew.starter.extension.crud.model.resp.R;

import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;

/**
 * 个人中心 API
 *
 * @author Charles7c
 * @since 2023/1/2 11:41
 */
@Tag(name = "个人中心 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
public class UserCenterController {

    private final UserService userService;
    private final UserSocialService userSocialService;
    private final AuthRequestFactory authRequestFactory;

    @Operation(summary = "上传头像", description = "用户上传个人头像")
    @PostMapping("/avatar")
    public R<AvatarResp> uploadAvatar(@NotNull(message = "头像不能为空") MultipartFile avatarFile) {
        ValidationUtils.throwIf(avatarFile::isEmpty, "头像不能为空");
        String newAvatar = userService.uploadAvatar(avatarFile, LoginHelper.getUserId());
        return R.ok("上传成功", AvatarResp.builder().avatar(newAvatar).build());
    }

    @Operation(summary = "修改基础信息", description = "修改用户基础信息")
    @PatchMapping("/basic/info")
    public R updateBasicInfo(@Validated @RequestBody UserBasicInfoUpdateReq updateReq) {
        userService.updateBasicInfo(updateReq, LoginHelper.getUserId());
        return R.ok("修改成功");
    }

    @Operation(summary = "修改密码", description = "修改用户登录密码")
    @PatchMapping("/password")
    public R updatePassword(@Validated @RequestBody UserPasswordUpdateReq updateReq) {
        String rawOldPassword =
            ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(updateReq.getOldPassword()));
        ValidationUtils.throwIfNull(rawOldPassword, "当前密码解密失败");
        String rawNewPassword =
            ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(updateReq.getNewPassword()));
        ValidationUtils.throwIfNull(rawNewPassword, "新密码解密失败");
        ValidationUtils.throwIf(!ReUtil.isMatch(RegexConstants.PASSWORD, rawNewPassword),
            "密码长度为 6 到 32 位，可以包含字母、数字、下划线，特殊字符，同时包含字母和数字");
        userService.updatePassword(rawOldPassword, rawNewPassword, LoginHelper.getUserId());
        return R.ok("修改成功");
    }

    @Operation(summary = "修改手机号", description = "修改手机号")
    @PatchMapping("/phone")
    public R updatePhone(@Validated @RequestBody UserPhoneUpdateReq updateReq) {
        String rawCurrentPassword =
            ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(updateReq.getCurrentPassword()));
        ValidationUtils.throwIfBlank(rawCurrentPassword, "当前密码解密失败");
        String captchaKey = RedisUtils.formatKey(CacheConstants.CAPTCHA_KEY_PREFIX, updateReq.getNewPhone());
        String captcha = RedisUtils.get(captchaKey);
        ValidationUtils.throwIfBlank(captcha, "验证码已失效");
        ValidationUtils.throwIfNotEqualIgnoreCase(updateReq.getCaptcha(), captcha, "验证码错误");
        RedisUtils.delete(captchaKey);
        userService.updatePhone(updateReq.getNewPhone(), rawCurrentPassword, LoginHelper.getUserId());
        return R.ok("修改成功");
    }

    @Operation(summary = "修改邮箱", description = "修改用户邮箱")
    @PatchMapping("/email")
    public R updateEmail(@Validated @RequestBody UserEmailUpdateRequest updateReq) {
        String rawCurrentPassword =
            ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(updateReq.getCurrentPassword()));
        ValidationUtils.throwIfBlank(rawCurrentPassword, "当前密码解密失败");
        String captchaKey = RedisUtils.formatKey(CacheConstants.CAPTCHA_KEY_PREFIX, updateReq.getNewEmail());
        String captcha = RedisUtils.get(captchaKey);
        ValidationUtils.throwIfBlank(captcha, "验证码已失效");
        ValidationUtils.throwIfNotEqualIgnoreCase(updateReq.getCaptcha(), captcha, "验证码错误");
        RedisUtils.delete(captchaKey);
        userService.updateEmail(updateReq.getNewEmail(), rawCurrentPassword, LoginHelper.getUserId());
        return R.ok("修改成功");
    }

    @Operation(summary = "查询绑定的三方账号", description = "查询绑定的三方账号")
    @GetMapping("/social")
    public R<List<UserSocialBindResp>> listSocialBind() {
        List<UserSocialDO> userSocialList = userSocialService.listByUserId(LoginHelper.getUserId());
        List<UserSocialBindResp> userSocialBindList = userSocialList.stream().map(userSocial -> {
            String source = userSocial.getSource();
            UserSocialBindResp userSocialBind = new UserSocialBindResp();
            userSocialBind.setSource(source);
            userSocialBind.setDescription(SocialSourceEnum.valueOf(source).getDescription());
            return userSocialBind;
        }).collect(Collectors.toList());
        return R.ok(userSocialBindList);
    }

    @Operation(summary = "绑定三方账号", description = "绑定三方账号")
    @Parameter(name = "source", description = "来源", example = "gitee", in = ParameterIn.PATH)
    @PostMapping("/social/{source}")
    public R bindSocial(@PathVariable String source, @RequestBody AuthCallback callback) {
        AuthRequest authRequest = authRequestFactory.get(source);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        ValidationUtils.throwIf(!response.ok(), response.getMsg());
        AuthUser authUser = response.getData();
        userSocialService.bind(authUser, LoginHelper.getUserId());
        return R.ok("绑定成功");
    }

    @Operation(summary = "解绑三方账号", description = "解绑三方账号")
    @Parameter(name = "source", description = "来源", example = "gitee", in = ParameterIn.PATH)
    @DeleteMapping("/social/{source}")
    public R unbindSocial(@PathVariable String source) {
        userSocialService.deleteBySourceAndUserId(source, LoginHelper.getUserId());
        return R.ok("解绑成功");
    }
}
