package com.insight.utils.pojo.base;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/4/9
 * @remark 切面VO
 */
public class DataSection<T> {

    /**
     * 切面ID
     */
    private Long id;

    /**
     * 切面名称
     */
    private String name;

    /**
     * 切面数据
     */
    private List<T> data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
