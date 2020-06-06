package net.bodz.lily.security.impl;

import net.bodz.lily.model.base.CoObjectMask;

/**
 * @see net.bodz.lily.security.UserOtherId
 */
public class UserOtherIdMask
        extends CoObjectMask {

    Integer userId;
    Integer typeId;
    String otherId;

    public UserOtherIdMask userId(Integer userId) {
        setUserId(userId);
        return this;
    }

    public UserOtherIdMask typeId(Integer typeId) {
        setTypeId(typeId);
        return this;
    }

    public UserOtherIdMask otherId(String otherId) {
        setOtherId(otherId);
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

}