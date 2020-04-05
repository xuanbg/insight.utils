package com.insight.utils.pojo;

import com.insight.utils.Json;

import java.io.Serializable;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 分页类
 */
public class PageConfig implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 第几页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 偏移量
     */
    private Integer offset;

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize == null ? 20 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset == null ? ((getPage() - 1) * getPageSize()) : offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
