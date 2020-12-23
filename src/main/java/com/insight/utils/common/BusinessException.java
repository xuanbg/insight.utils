package com.insight.utils.common;

/**
 * @author 宣炳刚
 * @date 2020/12/21
 * @remark 自定义业务异常类
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常消息
     */
    private final String message;

    /**
     * 构造方法
     * @param message 异常消息
     */
    public BusinessException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
