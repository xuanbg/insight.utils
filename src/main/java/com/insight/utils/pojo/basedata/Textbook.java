package com.insight.utils.pojo.basedata;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insight.utils.pojo.base.BaseXo;

import java.util.Objects;

/**
 * @author 宣炳刚
 * @date 2021/11/30
 * @remark 教材实体类
 */
public class Textbook extends BaseXo {

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 版本ID
     */
    private Long versionId;

    /**
     * XKW教材ID
     */
    private Long xkwId;

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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getXkwId() {
        return xkwId;
    }

    public void setXkwId(Long xkwId) {
        this.xkwId = xkwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Boolean nameMatch(String name) {
        return Objects.equals(this.name, name);
    }

    @JsonIgnore
    public Boolean versionMatch(Long versionId) {
        return Objects.equals(this.versionId, versionId);
    }
}
