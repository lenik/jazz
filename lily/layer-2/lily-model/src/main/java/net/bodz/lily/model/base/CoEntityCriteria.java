package net.bodz.lily.model.base;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;

import net.bodz.lily.model.sea.ParameterMapVariantMap;
import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.CoEntity
 */
public class CoEntityCriteria {

    String queryText;
    DateRange timeRange;

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

    public void populate(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
// IVariantMap<?> v;
// reqEx.getParameterMap();
    }

    protected void populate(QVariantMap<String> map)
            throws ParseException {
        queryText = map.getString("q", queryText);
        dateInterval = map.getDateInterval("dates", dateInterval);
        priorities = map.getInts("priorities", priorities);
        flags = map.getInts("flags", flags);
        states = map.getInts("states", states);
        ownerId = map.getInt("uid", ownerId);
        ownerGroupId = map.getInt("gid", ownerGroupId);
        acls = map.getInts("acls", acls);
    }

}
