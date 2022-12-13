package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2020/12/21
 * @remark 自定义业务异常类
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param message 异常消息
     */
    public BusinessException(String message) {
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param code    异常代码
     * @param message 异常消息
     */
    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    @Override
    public String getMessage() {
        return message;
    }
}
