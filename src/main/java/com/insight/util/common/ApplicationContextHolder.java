package com.insight.util.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 宣炳刚
 * @date 2017/9/9
 * @remark 应用上下文钩子
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * 设置应用上下文
     *
     * @param context 应用上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext context) {
        ApplicationContextHolder.context = context;
    }

    /**
     * 获取应用上下文
     *
     * @return 应用上下文
     */
    public static ApplicationContext getContext() {
        return context;
    }
}
