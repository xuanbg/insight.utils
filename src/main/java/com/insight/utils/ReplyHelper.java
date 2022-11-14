package com.insight.utils;

import com.insight.utils.pojo.base.Reply;

/**
 * @author 作者
 * @date 2017年9月5日
 * @remark Reply帮助类
 */
public final class ReplyHelper {

    /**
     * 账号不存在
     */
    private static final Reply ACCOUNT_EXIST_REPLY = new Reply();

    /**
     * 非法账号
     */
    private static final Reply INVALID_ACCOUNT_REPLY = new Reply();

    /**
     * 非法Code
     */
    private static final Reply INVALID_CODE_REPLY = new Reply();

    /**
     * 非法邮箱
     */
    private static final Reply INVALID_EMAIL_REPLY = new Reply();

    /**
     * 非法手机号
     */
    private static final Reply INVALID_PHONE_REPLY = new Reply();

    /**
     * 服务器错误
     */
    private static final Reply ERROR_REPLY = new Reply();

    static {
        //invalid account
        INVALID_ACCOUNT_REPLY.setCode(411);
        INVALID_ACCOUNT_REPLY.setMessage("非法的账户名");

        //account exist
        ACCOUNT_EXIST_REPLY.setCode(412);
        ACCOUNT_EXIST_REPLY.setMessage("用户已存在");

        //invalid code
        INVALID_CODE_REPLY.setCode(423);
        INVALID_CODE_REPLY.setMessage("验证码错误");

        //invalid phone
        INVALID_PHONE_REPLY.setCode(431);
        INVALID_PHONE_REPLY.setMessage("手机格式不正确");

        //invalid email
        INVALID_EMAIL_REPLY.setCode(432);
        INVALID_EMAIL_REPLY.setMessage("邮箱格式不正确");

        //error
        ERROR_REPLY.setCode(500);
        ERROR_REPLY.setMessage("服务器繁忙");
    }

    /**
     * 请求成功
     *
     * @return Reply
     */
    public static Reply success() {
        Reply reply = new Reply();
        reply.setCode(200);
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
        reply.setMessage(msg);

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
     * 非法口令
     *
     * @return Reply
     */
    public static Reply invalidPassword() {
        Reply reply = new Reply();
        reply.setCode(420);
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
        reply.setMessage(msg);

        return reply;
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
     * 服务端错误
     *
     * @param requestId 请求ID
     * @return Reply
     */
    public static Reply error(String requestId) {
        ERROR_REPLY.setOption(requestId);
        return ERROR_REPLY;
    }

    /**
     * 服务端错误
     *
     * @param requestId 请求ID
     * @param message   错误信息
     * @return Reply
     */
    public static Reply error(String requestId, String message) {
        ERROR_REPLY.setMessage(message);
        ERROR_REPLY.setOption(requestId);

        return ERROR_REPLY;
    }
}
