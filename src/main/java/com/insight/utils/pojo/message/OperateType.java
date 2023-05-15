package com.insight.utils.pojo.message;

/**
 * @author 宣炳刚
 * @date 2019-09-13
 * @remark
 */
public enum OperateType {

    /**
     * 查询操作
     */
    SELETE("查询数据"),

    /**
     * 新增操作
     */
    NEW("新增数据"),

    /**
     * 编辑操作
     */
    EDIT("修改数据"),

    /**
     * 备注操作
     */
    REMARK("备注"),

    /**
     * 编辑操作
     */
    DISABLE("禁用数据"),

    /**
     * 编辑操作
     */
    ENABLE("启用数据"),

    /**
     * 删除操作
     */
    DELETE("删除数据"),

    /**
     * 完成操作
     */
    COMPLETE("完成"),

    /**
     * 取消操作
     */
    CANCEL("取消");

    /**
     * 操作名称
     */
    private final String name;

    /**
     * 构造方法
     *
     * @param name 操作名称
     */
    OperateType(String name) {
        this.name = name;
    }

    /**
     * 获取操作名称
     *
     * @return 操作名称
     */
    public String getName() {
        return name;
    }
}
