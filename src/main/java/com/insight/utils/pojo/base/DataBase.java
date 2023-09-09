package com.insight.utils.pojo.base;

/**
 * @author 宣炳刚
 * @date 2023/9/9
 * @remark 基础类
 */
public class DataBase extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

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
}
