package net.bodz.lily.model.base;

import java.util.Set;

import net.bodz.bas.c.java.util.DateInterval;

/**
 * @see net.bodz.lily.model.base.CoEntity
 */
public class CoEntityCriteria {

    String queryText;
    DateInterval dateInterval;

    Set<Integer> priorities;
    Set<Integer> flags;
    Set<Integer> states;
    Integer ownerId;
    Integer ownerGroupId;
    Set<Integer> acls;

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public DateInterval getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(DateInterval dateInterval) {
        this.dateInterval = dateInterval;
    }

    public Set<Integer> getPriorities() {
        return priorities;
    }

    public void setPriorities(Set<Integer> priorities) {
        this.priorities = priorities;
    }

    public Set<Integer> getFlags() {
        return flags;
    }

    public void setFlags(Set<Integer> flags) {
        this.flags = flags;
    }

    public Set<Integer> getStates() {
        return states;
    }

    public void setStates(Set<Integer> states) {
        this.states = states;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerGroupId() {
        return ownerGroupId;
    }

    public void setOwnerGroupId(Integer ownerGroupId) {
        this.ownerGroupId = ownerGroupId;
    }

    public Set<Integer> getAcls() {
        return acls;
    }

    public void setAcls(Set<Integer> acls) {
        this.acls = acls;
    }

}
