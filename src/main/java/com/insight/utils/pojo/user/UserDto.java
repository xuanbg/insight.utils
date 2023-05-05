package com.insight.utils.pojo.user;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2023/5/5
 * @remark
 */
public class UserDto extends User {

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 机构ID
     */
    private Long orgId;

    /**
     * 用户授权角色ID集合
     */
    private List<Long> roleIds;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
