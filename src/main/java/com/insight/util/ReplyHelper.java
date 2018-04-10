package com.insight.util;


import com.insight.util.pojo.Reply;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author 作者
 * @date 2017年9月5日
 * @remark Reply帮助类
 */
public final class ReplyHelper {

    /**
     * 非法4XX
     */
    private static final Reply noAuthReply = new Reply();
    private static final Reply invalidTokenReply = new Reply();

    private static final Reply invalidAccountReply = new Reply();
    private static final Reply forbidReply = new Reply();
    private static final Reply overloadReply = new Reply();

    private static final Reply invalidEmailReply = new Reply();
    private static final Reply tooOftenReply = new Reply();
    private static final Reply invalidPhoneReply = new Reply();
    private static final Reply accountExistReply = new Reply();

    private static final Reply invalidCodeReply = new Reply();
    private static final Reply expiredTokenReply = new Reply();
    /**
     * 错误500
     */
    private static final Reply errorReply = new Reply();

    static {
        //
        noAuthReply.setCode(403);
        noAuthReply.setMessage("未授权");
        noAuthReply.setSuccess(false);

        //online overload
        overloadReply.setCode(410);
        overloadReply.setMessage("此账户在线人数超出上线");
        overloadReply.setSuccess(false);

        //invalid account
        invalidAccountReply.setCode(411);
        invalidAccountReply.setMessage("非法的账户名");
        invalidAccountReply.setSuccess(false);

        //account exist
        accountExistReply.setCode(412);
        accountExistReply.setMessage("用户已存在");
        accountExistReply.setSuccess(false);

        //forbidden
        forbidReply.setCode(413);
        forbidReply.setMessage("账户被禁止");
        forbidReply.setSuccess(false);

        //invalid token
        invalidTokenReply.setCode(421);
        invalidTokenReply.setMessage("无效凭证");
        invalidTokenReply.setSuccess(false);

        //expired token
        expiredTokenReply.setCode(422);
        expiredTokenReply.setMessage("凭证过期，需刷新");
        expiredTokenReply.setSuccess(false);

        //invalid code
        invalidCodeReply.setCode(423);
        invalidCodeReply.setMessage("验证码错误");
        invalidCodeReply.setSuccess(false);

        //invalid phone
        invalidPhoneReply.setCode(431);
        invalidPhoneReply.setMessage("手机格式不正确");
        invalidPhoneReply.setSuccess(false);

        //invalid email
        invalidEmailReply.setCode(432);
        invalidEmailReply.setMessage("邮箱格式不正确");
        invalidEmailReply.setSuccess(false);

        //请求过于频繁
        tooOftenReply.setCode(490);
        tooOftenReply.setMessage("您请求过于频繁，请稍后重试！");
        tooOftenReply.setSuccess(false);

        //error
        errorReply.setCode(500);
        errorReply.setMessage("服务器繁忙");
        errorReply.setSuccess(false);
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
     * @param msg 消息
     * @return
     */
    public static Reply success(String msg) {
        Reply reply = new Reply();
        reply.setCode(200);
        reply.setSuccess(true);
        reply.setMessage(msg);

        return reply;
    }

    /**
     * 请求成功
     * @param data 数据
     * @param msg 消息
     * @return
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
        return noAuthReply;
    }

    /**
     * 在线人数超载
     *
     * @return Reply
     */
    public static Reply overload() {
        return overloadReply;
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
        return accountExistReply;
    }

    /**
     * 非法账户
     *
     * @return Reply
     */
    public static Reply invalidAccount() {
        return invalidAccountReply;
    }

    /**
     * 禁止访问
     *
     * @return Reply
     */
    public static Reply forbid() {
        return forbidReply;
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
        return invalidTokenReply;
    }

    /**
     * token过期
     *
     * @return Reply
     */
    public static Reply expiredToken() {
        return expiredTokenReply;
    }

    /**
     * 非法code
     *
     * @return Reply
     */
    public static Reply invalidCode() {
        return invalidCodeReply;
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
        return invalidPhoneReply;
    }

    /**
     * 非法email
     *
     * @return Reply
     */
    public static Reply invalidEmail() {
        return invalidEmailReply;
    }

    /**
     * 请求过于频繁
     *
     * @return Reply
     */
    public static Reply tooOften() {
        return tooOftenReply;
    }

    /**
     * 请求过于频繁
     *
     * @param obj 数据对象
     * @return
     */
    public static Reply tooOften(Object obj) {
        try {
            Reply reply = (Reply) BeanUtils.cloneBean(tooOftenReply);
            reply.setData(obj);

            return reply;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 服务端错误
     *
     * @return Reply
     */
    public static Reply error() {
        return errorReply;
    }
}
