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
     * 主键
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 所有者ID
     */
    private String ownerId;

    /**
     * 类型
     */
    private Integer type;

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
     * 是否仅有效数据:null.全部数据,true.仅失效数据,false.仅有效数据
     */
    private Boolean invalid;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate == null ? null : endDate.plusDays(1);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
