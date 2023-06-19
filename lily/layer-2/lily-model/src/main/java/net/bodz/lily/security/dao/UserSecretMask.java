package net.bodz.lily.security.dao;

public class UserSecretMask
        extends _UserSecretMask_stuff {

    String userName;
    String phone;
    String email;

    public UserSecretMask() {
    }

    public UserSecretMask(Integer userId, String userName) {
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

    public UserSecretMask userId(int id) {
        setUserId(id);
        return this;
    }

    public UserSecretMask userName(String name) {
        this.userName = name;
        return this;
    }

    public UserSecretMask password(String password) {
        this.password = password;
        return this;
    }

    public UserSecretMask phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserSecretMask email(String email) {
        this.email = email;
        return this;
    }

}
