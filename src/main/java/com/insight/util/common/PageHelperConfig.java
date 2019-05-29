package com.insight.util.common;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author 宣炳刚
 * @date 2018/2/2
 * @remark
 */
@Configuration
public class PageHelperConfig {

    /**
     * 配置mybatis的分页插件pageHelper
     *
     * @return PageHelper
     */
    @Bean
    public PageHelper pageHelper() {
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");

        // 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用，和startPage中的pageNum效果一样
        properties.setProperty("offsetAsPageNum", "true");

        // 设置为true时，使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount", "true");

        // 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
        properties.setProperty("pageSizeZero", "true");

        // 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        properties.setProperty("reasonable", "false");

        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}