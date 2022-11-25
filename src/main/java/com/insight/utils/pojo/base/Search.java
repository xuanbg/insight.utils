package com.insight.utils.pojo.base;

import com.insight.utils.Util;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019/05/20
 * @remark 分页类
 */
public class Search extends BaseXo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 所有者ID
     */
    private Long ownerId;

    /**
     * 所有者
     */
    private String owner;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 代码
     */
    private String code;

    /**
     * 查询关键词
     */
    private String keyword;

    /**
     * 关键值
     */
    private String value;

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

    /**
     * 范围集
     */
    private List<String> stringSet;

    /**
     * 范围集
     */
    private List<Long> longSet;

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 最后ID
     */
    private Long LastId;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 构造方法
     */
    public Search() {
    }

    /**
     * 构造方法
     *
     * @param id 主键
     */
    public Search(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyword() {
        return Util.isEmpty(keyword) ? null : keyword.replace("%", "\\%");
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public List<String> getStringSet() {
        return stringSet;
    }

    public void setStringSet(List<String> stringSet) {
        this.stringSet = stringSet;
    }

    public List<Long> getLongSet() {
        return longSet;
    }

    public void setLongSet(List<Long> longSet) {
        this.longSet = longSet;
    }

    public Integer getPageNum() {
        return pageNum == null || pageNum <= 1 ? 1 : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize < 10 ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getLastId() {
        return LastId;
    }

    public void setLastId(Long lastId) {
        LastId = lastId;
    }

    public Integer getOffset() {
        return offset == null ? ((getPageNum() - 1) * getPageSize()) : offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderBy() {
        if (orderBy == null || !orderBy.matches("^[0-9a-zA-Z_,`' ]*$")) {
            return "id desc";
        } else {
            orderBy = orderBy.replace("DESC", "desc").replace("ASC", "asc");
            return Util.camelToUnderScore(orderBy);
        }
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
