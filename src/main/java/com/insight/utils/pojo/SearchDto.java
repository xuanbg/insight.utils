package com.insight.utils.pojo;

import com.insight.utils.Json;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 分页类
 */
public class SearchDto implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 查询关键词
     */
    private String keyword;

    /**
     * 第几页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 截止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page == null ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size == null ? 20 : size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset == null ? (getSize() * (getPage() - 1)) : offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
