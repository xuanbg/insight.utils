package com.insight.utils;

import com.insight.utils.http.HttpClientUtil;
import com.insight.utils.pojo.base.BusinessException;
import com.insight.utils.pojo.wechat.WechatToken;
import com.insight.utils.pojo.wechat.WechatUser;


/**
 * @author luwenbao
 * @date 2018/1/5.
 * @remark 微信相关帮助类
 */
public final class WechatHelper {
    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 获取微信用户信息
     *
     * @param code        微信授权码
     * @param weChatAppId 微信appId
     * @param secret      微信appId 秘钥
     * @return WeChatUser
     */
    public static WechatUser getUserInfo(String code, String weChatAppId, String secret) {
        String url = buildGetTokenUrl(code, weChatAppId, secret);
        String tokenJson = HttpClientUtil.httpClientGet(url,null,"utf-8");
        if (!checkWeChatResult(tokenJson)) {
            String msg = "AppID is: %s\r\nCode is: %s\r\nSecret is: %s\r\nResult is:%s".formatted(weChatAppId, code, secret, tokenJson);
            throw new BusinessException(msg);
        }

        WechatToken token = Json.toBean(tokenJson, WechatToken.class);
        if (token == null) {
            throw new BusinessException(tokenJson);
        }

        url = buildGetUserInfoUrl(token.getOpenid(), token.getAccess_token());
        String resultJson = HttpClientUtil.httpClientGet(url,null,"utf-8");
        if (!checkWeChatResult(resultJson)) {
            String msg = "AppID is: %s\r\nToken is: %s\r\nResult is:%s".formatted(weChatAppId, token.getAccess_token(), resultJson);
            throw new BusinessException(msg);
        }

        return Json.toBean(resultJson, WechatUser.class);
    }

    /**
     * 检查微信接口返回值
     *
     * @param resultJson 返回值
     * @return 是否合法
     */
    private static Boolean checkWeChatResult(String resultJson) {
        return Json.toMap(resultJson).containsKey("openid");
    }

    /**
     * 创建获取微信token的访问url
     *
     * @param code        微信授权码
     * @param weChatAppId 微信appId
     * @param secret      微信公共号秘钥
     * @return URL
     */
    private static String buildGetTokenUrl(String code, String weChatAppId, String secret) {
        return String.format("%s?appid=%s&secret=%s&code=%s&grant_type=authorization_code", GET_TOKEN_URL, weChatAppId, secret, code);
    }

    /**
     * 创建获取用户信息的访问url
     *
     * @param openId      微信OpenID
     * @param accessToken 微信访问令牌
     * @return URL
     */
    private static String buildGetUserInfoUrl(String openId, String accessToken) {
        return String.format("%s?access_token=%s&openid=%s&lang=zh_CN", GET_USER_INFO_URL, accessToken, openId);
    }
}
