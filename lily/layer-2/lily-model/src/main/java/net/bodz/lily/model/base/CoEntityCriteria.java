package net.bodz.lily.model.base;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;
import net.bodz.bas.t.variant.IVariantMap;

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

    public CoEntityCriteria setQueryText(String queryText) {
        this.queryText = queryText;
        return this;
    }

    public DateRange getTimeRange() {
        return timeRange;
    }

    public CoEntityCriteria setTimeRange(DateRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public Set<Integer> getPriorities() {
        return priorities;
    }

    public CoEntityCriteria setPriorities(Set<Integer> priorities) {
        this.priorities = priorities;
        return this;
    }

    public Set<Integer> getFlags() {
        return flags;
    }

    public CoEntityCriteria setFlags(Set<Integer> flags) {
        this.flags = flags;
        return this;
    }

    public Set<Integer> getStates() {
        return states;
    }

    public CoEntityCriteria setStates(Set<Integer> states) {
        this.states = states;
        return this;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public CoEntityCriteria setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Integer getOwnerGroupId() {
        return ownerGroupId;
    }

    public CoEntityCriteria setOwnerGroupId(Integer ownerGroupId) {
        this.ownerGroupId = ownerGroupId;
        return this;
    }

    public Set<Integer> getAcls() {
        return acls;
    }

    public CoEntityCriteria setAcls(Set<Integer> acls) {
        this.acls = acls;
        return this;
    }

    public final void populate(IVariantMap<String> variantMap)
            throws ParseException {
        QVariantMap<String> qMap = new QVariantMap<>(variantMap);
        populate(qMap);
    }

    public final void populate(Map<String, String[]> parameterMap)
            throws ParseException {
        ParameterMapVariantMap variantMap = new ParameterMapVariantMap(parameterMap);
        QVariantMap<String> qMap = new QVariantMap<>(variantMap);
        populate(qMap);
    }

    protected void populate(QVariantMap<String> map)
            throws ParseException {
        queryText = map.getString("q", queryText);
        timeRange = map.getDateRange("times", timeRange);
        priorities = map.getInts("priorities", priorities);
        flags = map.getInts("flags", flags);
        states = map.getInts("states", states);
        ownerId = map.getInt("uid", ownerId);
        ownerGroupId = map.getInt("gid", ownerGroupId);
        acls = map.getInts("acls", acls);
    }

}
