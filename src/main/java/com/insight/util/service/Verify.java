package com.insight.util.service;

import com.insight.util.Json;
import com.insight.util.ReplyHelper;
import com.insight.util.Util;
import com.insight.util.common.ApplicationContextHolder;
import com.insight.util.pojo.AccessToken;
import com.insight.util.pojo.Reply;
import com.insight.util.pojo.TokenInfo;
import com.insight.util.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 用户身份验证类
 */
public class Verify {
    private final Logger logger;
    private final StringRedisTemplate redis;
    private final String hash;

    private TokenInfo basis = null;
    private UserInfo info = null;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 构造方法
     *
     * @param token     访问令牌
     * @param userAgent 用户信息
     */
    public Verify(String token, String userAgent) {
        this.redis = ApplicationContextHolder.getContext().getBean(StringRedisTemplate.class);
        this.logger = LoggerFactory.getLogger(this.getClass());

        // 初始化参数
        hash = Util.md5(token + userAgent);
        AccessToken accessToken = Json.toAccessToken(token);
        if (accessToken == null) {
            logger.error("提取验证信息失败。TokenManage is:" + token);

            return;
        }

        userId = accessToken.getUserId();
        basis = getToken(accessToken.getId());
        info = getUserInfo();
    }

    /**
     * 验证Token合法性
     *
     * @return Reply Token验证结果
     */
    public Reply compare() {
        return compare(null);
    }

    /**
     * 验证Token合法性
     *
     * @param function 功能ID或URL
     * @return Reply Token验证结果
     */
    public Reply compare(String function) {
        if (basis == null) {
            return ReplyHelper.invalidToken();
        }

        // 验证令牌
        if (!basis.verifyToken(hash)) {
            return ReplyHelper.invalidToken();
        }

        if (basis.isExpiry(true)) {
            return ReplyHelper.expiredToken();
        }

        if (basis.isFailure()) {
            return ReplyHelper.invalidToken();
        }

        if (info == null || info.getInvalid()) {
            return ReplyHelper.fail("用户已被禁用");
        }

        // 无需鉴权,返回成功
        if (function == null || function.isEmpty()) {
            return ReplyHelper.success();
        }

        // 进行鉴权,返回鉴权结果
        if (isPermit(function)) {
            return ReplyHelper.success();
        }

        logger.warn("用户『" + info.getAccount() + "』试图使用未授权的功能:" + function);
        return ReplyHelper.noAuth();
    }

    /**
     * 获取令牌中的用户ID
     *
     * @return 是否同一用户
     */
    public boolean userIsEquals(String userId) {
        return this.userId.equals(userId);
    }

    /**
     * 根据令牌ID获取缓存中的Token
     *
     * @param tokenId 令牌ID
     * @return TokenInfo(可能为null)
     */
    private TokenInfo getToken(String tokenId) {
        String key = "Token:" + tokenId;
        String json = redis.opsForValue().get(key);
        if (json == null || json.isEmpty()) {
            return null;
        }

        return Json.toBean(json, TokenInfo.class);
    }

    /**
     * 根据用户ID获取缓存中的用户信息
     *
     * @return UserInfo(可能为null)
     */
    private UserInfo getUserInfo() {
        String key = "User:" + userId;
        String json = redis.opsForValue().get(key);
        if (json == null || json.isEmpty()) {
            return null;
        }

        return Json.toBean(json, UserInfo.class);
    }

    /**
     * 指定的功能是否授权给用户
     *
     * @param function 功能ID或功能对应接口URL
     * @return 功能是否授权给用户
     */
    private Boolean isPermit(String function) {
        List<String> functions = basis.getPermitFuncs();

        return functions.stream().anyMatch(i -> i.contains(function));
    }
}
