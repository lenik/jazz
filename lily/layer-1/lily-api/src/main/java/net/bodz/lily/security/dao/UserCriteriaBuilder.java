package net.bodz.lily.security.dao;

public class UserCriteriaBuilder
        extends _UserCriteriaBuilder_stuff {

    Integer categoryId;
    Integer primaryGroupId;
    Integer groupId;

    String password;
    String email;
    String phone;

    public UserCriteriaBuilder name(String name) {
        setName(name);
        return this;
    }

    public UserCriteriaBuilder categoryId(Integer categoryId) {
        setCategoryId(categoryId);
        return this;
    }

    public UserCriteriaBuilder primaryGroupId(Integer primaryGroupId) {
        setPrimaryGroupId(primaryGroupId);
        return this;
    }

    public UserCriteriaBuilder groupId(Integer groupId) {
        setGroupId(groupId);
        return this;
    }

    public UserCriteriaBuilder password(String password) {
        setPassword(password);
        return this;
    }

    public UserCriteriaBuilder email(String email) {
        setEmail(email);
        return this;
    }

    public UserCriteriaBuilder phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public Integer getPrimaryGroupId() {
        return primaryGroupId;
    }

    @Override
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
