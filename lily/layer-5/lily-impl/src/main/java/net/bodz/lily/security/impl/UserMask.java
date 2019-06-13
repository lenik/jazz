package net.bodz.lily.security.impl;

/**
 * @see net.bodz.lily.security.User
 */
public class UserMask
        extends CoPrincipalMask {

    Integer categoryId;
    Integer primaryGroupId;
    Integer groupId;

    String password;
    String email;
    String mobile;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrimaryGroupId() {
        return primaryGroupId;
    }

    public void setPrimaryGroupId(Integer primaryGroupId) {
        this.primaryGroupId = primaryGroupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
