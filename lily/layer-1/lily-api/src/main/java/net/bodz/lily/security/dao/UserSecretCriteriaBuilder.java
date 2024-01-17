package net.bodz.lily.security.dao;

public class UserSecretCriteriaBuilder
        extends _UserSecretCriteriaBuilder_stuff {

    String userName;
    String phone;
    String email;

    public UserSecretCriteriaBuilder() {
    }

    public UserSecretCriteriaBuilder(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserSecretCriteriaBuilder userId(int id) {
        setUserId(id);
        return this;
    }

    public UserSecretCriteriaBuilder userName(String name) {
        this.userName = name;
        return this;
    }

    public UserSecretCriteriaBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserSecretCriteriaBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserSecretCriteriaBuilder email(String email) {
        this.email = email;
        return this;
    }

}
