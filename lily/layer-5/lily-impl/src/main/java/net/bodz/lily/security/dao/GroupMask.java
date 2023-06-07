package net.bodz.lily.security.dao;

/**
 * @see net.bodz.lily.security.Group
 */
public class GroupMask
        extends CoPrincipalMask {

    Integer roleId;
    Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
