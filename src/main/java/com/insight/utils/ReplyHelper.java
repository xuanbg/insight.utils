package com.insight.utils;

import com.insight.utils.pojo.Reply;

/**
 * @author 作者
 * @date 2017年9月5日
 * @remark Reply帮助类
 */
public final class ReplyHelper {

    /**
     * 未授权
     */
    private static final Reply NO_AUTH_REPLY = new Reply();

    /**
     * 非法Token
     */
    private static final Reply INVALID_TOKEN_REPLY = new Reply();

    /**
     * 非法账号
     */
    private static final Reply INVALID_ACCOUNT_REPLY = new Reply();

    /**
     * 未授权
     */
    private static final Reply FORBID_REPLY = new Reply();

    /**
     * 用户已达上限
     */
    private static final Reply OVERLOAD_REPLY = new Reply();

    /**
     * 非法邮箱
     */
    private static final Reply INVALID_EMAIL_REPLY = new Reply();

    /**
     * 访问过于频繁
     */
    private static final Reply TOO_OFTEN_REPLY = new Reply();

    /**
     * 非法手机号
     */
    private static final Reply INVALID_PHONE_REPLY = new Reply();
    
    /**
     * 账号不存在
     */
    private static final Reply ACCOUNT_EXIST_REPLY = new Reply();

    /**
     * 非法Code
     */
    private static final Reply INVALID_CODE_REPLY = new Reply();

    /**
     * Token过期
     */
    private static final Reply EXPIRED_TOKEN_REPLY = new Reply();

    /**
     * 服务器错误
     */
    private static final Reply ERROR_REPLY = new Reply();

    static {
        //
        NO_AUTH_REPLY.setCode(403);
        NO_AUTH_REPLY.setMessage("未授权");
        NO_AUTH_REPLY.setSuccess(false);

        //online overload
        OVERLOAD_REPLY.setCode(410);
        OVERLOAD_REPLY.setMessage("此账户在线人数超出上线");
        OVERLOAD_REPLY.setSuccess(false);

        //invalid account
        INVALID_ACCOUNT_REPLY.setCode(411);
        INVALID_ACCOUNT_REPLY.setMessage("非法的账户名");
        INVALID_ACCOUNT_REPLY.setSuccess(false);

        //account exist
        ACCOUNT_EXIST_REPLY.setCode(412);
        ACCOUNT_EXIST_REPLY.setMessage("用户已存在");
        ACCOUNT_EXIST_REPLY.setSuccess(false);

        //forbidden
        FORBID_REPLY.setCode(413);
        FORBID_REPLY.setMessage("账户被禁止使用");
        FORBID_REPLY.setSuccess(false);

        //invalid token
        INVALID_TOKEN_REPLY.setCode(421);
        INVALID_TOKEN_REPLY.setMessage("无效凭证");
        INVALID_TOKEN_REPLY.setSuccess(false);

        //expired token
        EXPIRED_TOKEN_REPLY.setCode(422);
        EXPIRED_TOKEN_REPLY.setMessage("凭证过期，需刷新");
        EXPIRED_TOKEN_REPLY.setSuccess(false);

        //invalid code
        INVALID_CODE_REPLY.setCode(423);
        INVALID_CODE_REPLY.setMessage("验证码错误");
        INVALID_CODE_REPLY.setSuccess(false);

        //invalid phone
        INVALID_PHONE_REPLY.setCode(431);
        INVALID_PHONE_REPLY.setMessage("手机格式不正确");
        INVALID_PHONE_REPLY.setSuccess(false);

        //invalid email
        INVALID_EMAIL_REPLY.setCode(432);
        INVALID_EMAIL_REPLY.setMessage("邮箱格式不正确");
        INVALID_EMAIL_REPLY.setSuccess(false);

        //请求过于频繁
        TOO_OFTEN_REPLY.setCode(490);
        TOO_OFTEN_REPLY.setMessage("您请求过于频繁，请稍后重试！");
        TOO_OFTEN_REPLY.setSuccess(false);

        //error
        ERROR_REPLY.setCode(500);
        ERROR_REPLY.setMessage("服务器繁忙");
        ERROR_REPLY.setSuccess(false);
    }

    /**
     * 请求成功
     *
     * @return Reply
     */
    public static Reply success() {
        Reply reply = new Reply();
        reply.setCode(200);
        reply.setSuccess(true);
        reply.setMessage("请求成功");

        return reply;
    }

    /**
     * 请求成功
     *
     * @param data 数据
     * @return Reply
     */
    public static Reply success(Object data) {
        Reply reply = new Reply();
        reply.setCode(200);
        reply.setSuccess(true);
        reply.setData(data);
        reply.setMessage("请求成功");

        return reply;
    }

    /**
     * 请求成功
     *
     * @param data   数据
     * @param option 选项
     * @return Reply
     */
    public static Reply success(Object data, Object option) {
        Reply reply = new Reply();
        reply.setCode(200);
        reply.setSuccess(true);
        reply.setData(data);
        reply.setOption(option);
        reply.setMessage("请求成功");

        return reply;
    }
    
    /**
     * 请求成功
     *
     * @param data   数据
     * @param option 选项
     * @param msg    提示信息
     * @return Reply
     */
    public static Reply success(Object data, Object option, String msg) {
        Reply reply = new Reply();
        reply.setCode(200);
        reply.setSuccess(true);
        reply.setData(data);
        reply.setOption(option);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 请求成功
     *
     * @param data 数据
     * @param msg  消息
     * @return Reply
     */
    public static Reply success(Object data, String msg) {
        Reply reply = new Reply();
        reply.setCode(200);
        reply.setSuccess(true);
        reply.setData(data);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 创建数据成功
     *
     * @param data 数据
     * @return Reply
     */
    public static Reply created(Object data) {
        Reply reply = new Reply();
        reply.setCode(201);
        reply.setSuccess(true);
        reply.setData(data);
        reply.setMessage("创建数据成功");

        return reply;
    }

    /**
     * 服务端错误
     *
     * @return Reply
     */
    public static Reply fail() {
        Reply reply = new Reply();
        reply.setCode(400);
        reply.setSuccess(false);
        reply.setMessage("请求失败");

        return reply;
    }

    /**
     * 服务端错误
     *
     * @param msg 消息
     * @return Reply
     */
    public static Reply fail(String msg) {
        Reply reply = new Reply();
        reply.setCode(400);
        reply.setSuccess(false);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 在线人数超载
     *
     * @return Reply
     */
    public static Reply noAuth() {
        return NO_AUTH_REPLY;
    }

    /**
     * 在线人数超载
     *
     * @return Reply
     */
    public static Reply overload() {
        return OVERLOAD_REPLY;
    }

    /**
     * 用户不存在
     *
     * @return Reply
     */
    public static Reply notExist() {
        Reply reply = new Reply();
        reply.setCode(414);
        reply.setSuccess(false);
        reply.setMessage("用户不存在");

        return reply;
    }

    /**
     * 用户不存在
     *
     * @return Reply
     */
    public static Reply notExist(Object data) {
        Reply reply = new Reply();
        reply.setCode(414);
        reply.setSuccess(false);
        reply.setData(data);
        reply.setMessage("用户不存在");

        return reply;
    }

    /**
     * 用户不存在
     *
     * @param msg 消息
     * @return Reply
     */
    public static Reply notExist(String msg) {
        Reply reply = new Reply();
        reply.setCode(414);
        reply.setSuccess(false);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 非法账户
     *
     * @return Reply
     */
    public static Reply accountExist() {
        return ACCOUNT_EXIST_REPLY;
    }

    /**
     * 非法账户
     *
     * @return Reply
     */
    public static Reply invalidAccount() {
        return INVALID_ACCOUNT_REPLY;
    }

    /**
     * 禁止访问
     *
     * @return Reply
     */
    public static Reply forbid() {
        return FORBID_REPLY;
    }

    /**
     * 非法口令
     *
     * @return Reply
     */
    public static Reply invalidPassword() {
        Reply reply = new Reply();
        reply.setCode(420);
        reply.setSuccess(false);
        reply.setMessage("密码错误");

        return reply;
    }

    /**
     * 非法口令
     *
     * @param msg 消息
     * @return Reply
     */
    public static Reply invalidPassword(String msg) {
        Reply reply = new Reply();
        reply.setCode(420);
        reply.setSuccess(false);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 非法token
     *
     * @return Reply
     */
    public static Reply invalidToken() {
        return INVALID_TOKEN_REPLY;
    }

    /**
     * token过期
     *
     * @return Reply
     */
    public static Reply expiredToken() {
        return EXPIRED_TOKEN_REPLY;
    }

    /**
     * 非法code
     *
     * @return Reply
     */
    public static Reply invalidCode() {
        return INVALID_CODE_REPLY;
    }

    /**
     * 无效参数
     *
     * @return Reply
     */
    public static Reply invalidParam() {
        Reply reply = new Reply();
        reply.setCode(430);
        reply.setSuccess(false);
        reply.setMessage("无效参数");

        return reply;
    }

    /**
     * 无效参数
     *
     * @param msg 消息
     * @return Reply
     */
    public static Reply invalidParam(String msg) {
        Reply reply = new Reply();
        reply.setCode(430);
        reply.setSuccess(false);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 非法电话号码
     *
     * @return Reply
     */
    public static Reply invalidPhone() {
        return INVALID_PHONE_REPLY;
    }

    /**
     * 非法email
     *
     * @return Reply
     */
    public static Reply invalidEmail() {
        return INVALID_EMAIL_REPLY;
    }

    /**
     * 请求过于频繁
     *
     * @return Reply
     */
    public static Reply tooOften() {
        return TOO_OFTEN_REPLY;
    }

    /**
     * 请求过于频繁
     *
     * @param msg 错误消息
     * @return Reply
     */
    public static Reply tooOften(String msg) {
        if (msg != null && !msg.isEmpty()) {
            TOO_OFTEN_REPLY.setMessage(msg);
        }

        return TOO_OFTEN_REPLY;
    }

    /**
     * 服务端错误
     *
     * @return Reply
     */
    public static Reply error() {
        return ERROR_REPLY;
    }
}
