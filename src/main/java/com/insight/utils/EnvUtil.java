package com.insight.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author 宣炳刚
 * @date 2022/11/25
 * @remark
 */
@Component
public class EnvUtil implements EnvironmentAware {
    private static Environment environment;

    /**
     * 设置环境变量
     *
     * @param environment Environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        EnvUtil.environment = environment;
    }

    /**
     * 读取Key对应的配置值
     *
     * @param key 配置Key
     * @return 配置值
     */
    public static String getValue(String key) {
        return environment.getProperty(key);
    }
}
