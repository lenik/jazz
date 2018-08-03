package net.bodz.lily.security.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.lily.security.UserSecret
 */
public class UserSecretMask
        extends CoObjectMask {

    Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
