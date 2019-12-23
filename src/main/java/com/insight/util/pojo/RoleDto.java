package com.insight.util.pojo;

import com.insight.util.Json;

import java.io.Serializable;
import java.util.List;

/**
 * @author 宣炳刚
 * @date 2019/12/23
 * @remark
 */
public class RoleDto implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 角色成员集合
     */
    private List<MemberDto> members;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private String creatorId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<MemberDto> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDto> members) {
        this.members = members;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
