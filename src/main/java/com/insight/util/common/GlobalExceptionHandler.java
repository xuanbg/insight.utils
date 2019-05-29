package com.insight.util.common;

import com.insight.util.ReplyHelper;
import com.insight.util.pojo.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Objects;
import java.util.Set;

/**
 * @author luwenbao
 * @date 2018/4/18
 * @remark 全局异常捕获 统一处理
 */
@ResponseStatus(HttpStatus.OK)
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理缺少请求参数的异常 以http状态200 返回
     *
     * @param e 资源不存在
     * @return Reply
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Reply handleNoHandlerFoundException(NoHandlerFoundException e) {
        logger.error("资源不存在 请求URL不存在 NoHandlerFoundException", e);
        return ReplyHelper.notExist("资源不存在");
    }

    /**
     * 处理缺少请求参数的异常 以http状态200 返回
     *
     * @param e 缺少请求参数
     * @return Reply
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Reply handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数 MissingServletRequestParameterException", e);
        return ReplyHelper.fail("缺少请求参数");
    }

    /**
     * 处理不合法的参数的异常 以http状态200 返回
     *
     * @param e 不合法的参数异常
     * @return Reply
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Reply handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("不合法的参数 IllegalArgumentException", e);
        return ReplyHelper.invalidParam("不合法的参数");
    }

    /**
     * 处理参数绑定出现的异常 以http状态200 返回
     *
     * @param e 参数绑定错误异常
     * @return Reply
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public Reply handleServletRequestBindingException(ServletRequestBindingException e) {
        logger.error("参数绑定错误 ServletRequestBindingException", e);
        return ReplyHelper.invalidParam(e.getMessage());
    }

    /**
     * 处理参数解析失败的异常 以http状态200 返回
     *
     * @param e 参数解析失败异常
     * @return Reply
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Reply handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败 HttpMessageNotReadableException", e);
        return ReplyHelper.invalidParam("参数解析失败");
    }

    /**
     * 参数验证失败的异常 以http状态200 返回
     *
     * @param e 参数验证失败异常
     * @return Reply
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Reply handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败 MethodArgumentNotValidException", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        return ReplyHelper.invalidParam(Objects.requireNonNull(error).getDefaultMessage());
    }

    /**
     * 参数绑定失败的异常 以http状态200 返回
     *
     * @param e 参数绑定失败异常
     * @return Reply
     */
    @ExceptionHandler(BindException.class)
    public Reply handleBindException(BindException e) {
        logger.error("参数绑定失败 BindException", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = Objects.requireNonNull(error).getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return ReplyHelper.invalidParam(message);
    }

    /**
     * 参数违反唯一约束的异常 以http状态200 返回
     *
     * @param e 违反唯一约束异常
     * @return Reply
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Reply handleServiceException(ConstraintViolationException e) {
        logger.error("参数违反唯一约束 ConstraintViolationException", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return ReplyHelper.invalidParam("违反唯一约束 parameter:" + message);
    }


    /**
     * 请求方法不允许的异常 以http状态200 返回
     *
     * @param e 请求方法不允许异常
     * @return Reply
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Reply handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法 HttpRequestMethodNotSupportedException", e);
        return ReplyHelper.fail("不支持当前请求方法");
    }

    /**
     * 参数类型不匹配的异常 以http状态200 返回
     *
     * @param e 参数类型不匹配异常
     * @return Reply
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Reply handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error("不支持当前媒体类型 HttpMediaTypeNotSupportedException", e);
        return ReplyHelper.invalidParam("不支持当前媒体类型");
    }

    /**
     * 非预期类型的异常 以http状态200 返回
     *
     * @param e 非预期类型异常
     * @return Reply
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public Reply handleUnexpectedTypeException(UnexpectedTypeException e) {
        logger.error("非预期类型 UnexpectedTypeException", e);
        return ReplyHelper.fail("类型不匹配");
    }


    /**
     * 数据库操作出现异常：插入、删除和修改数据的时候，违背数据完整性约束抛出的异常  以http状态200 返回
     *
     * @param e 违背数据完整性约异常
     * @return Reply
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Reply handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error("操作数据库出现异常:", e);
        return ReplyHelper.fail("操作数据库出现异常");
    }


    /**
     * 服务器异常 以http状态200 返回
     *
     * @param e 通用异常
     * @return Reply
     */
    @ExceptionHandler(Exception.class)
    public Reply handleException(Exception e) {
        logger.error("通用异常，业务处理失败", e);
        return ReplyHelper.fail("业务处理失败");
    }


    /**
     * 服务器异常 以http状态200 返回
     *
     * @param e 服务器异常
     * @return Reply
     */
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public Reply handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        logger.error("服务器异常  AsyncRequestTimeoutException", e);
        return ReplyHelper.error();
    }

    /**
     * 运行时异常 以http状态200 返回
     *
     * @param e 运行时异常
     * @return Reply
     */
    @ExceptionHandler(RuntimeException.class)
    public Reply handleRuntimeException(RuntimeException e) {
        logger.error("运行时异常 RuntimeException", e);
        return ReplyHelper.error();
    }
}

