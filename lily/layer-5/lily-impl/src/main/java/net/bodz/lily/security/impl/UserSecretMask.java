package net.bodz.lily.security.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.lily.security.UserSecret
 */
public class UserSecretMask
        extends CoObjectMask {

    Integer userId;
    String userName;
    String password;
    String mobile;
    String email;

    public UserSecretMask() {
    }

    public UserSecretMask(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSecretMask userId(int id) {
        this.userId = id;
        return this;
    }

    public UserSecretMask userName(String name) {
        this.userName = name;
        return this;
    }

    public UserSecretMask mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserSecretMask email(String email) {
        this.email = email;
        return this;
    }

}
