package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoMomentIntervalCriteriaBuilder;
import net.bodz.lily.security.User;

/**
 * @see net.bodz.lily.security.UserOtherId
 */
public class UserOtherIdCriteriaBuilder
        extends CoMomentIntervalCriteriaBuilder {

    Integer userId;
    Integer typeId;
    String otherId;

    public UserOtherIdCriteriaBuilder user(User user) {
        this.userId = user.id();
        return this;
    }

    public UserOtherIdCriteriaBuilder userId(Integer userId) {
        setUserId(userId);
        return this;
    }

    public UserOtherIdCriteriaBuilder typeId(Integer typeId) {
        setTypeId(typeId);
        return this;
    }

    public UserOtherIdCriteriaBuilder otherId(String otherId) {
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
