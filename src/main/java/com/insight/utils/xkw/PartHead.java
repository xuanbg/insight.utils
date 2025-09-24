package com.insight.utils.xkw;

import com.insight.utils.pojo.base.BaseXo;

/**
 * @author 宣炳刚
 * @date 2025/9/1
 * @remark
 */
public class PartHead extends BaseXo {

    /**
     * 名称
     */
    private String name;

    /**
     * 说明
     */
    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
