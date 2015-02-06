package net.bodz.lily.model.base;

import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.bodz.bas.c.type.TypeNearby;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;

import net.bodz.lily.model.base.security.LoginContext;
import net.bodz.lily.model.sea.AbstractTextParametric;
import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class CoObjectCriteria
        extends AbstractTextParametric {

    String codeName;
    String label;
    String description;

    String queryText;
    DateRange timeRange;

    Set<Integer> priorities;
    Set<Integer> flags;
    Set<Integer> states;
    Integer ownerId;
    Integer ownerGroupId;
    Set<Integer> acls;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQueryText() {
        return queryText;
    }

    public CoObjectCriteria setQueryText(String queryText) {
        this.queryText = queryText;
        return this;
    }

    public DateRange getTimeRange() {
        return timeRange;
    }

    public CoObjectCriteria setTimeRange(DateRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public Set<Integer> getPriorities() {
        return priorities;
    }

    public CoObjectCriteria setPriorities(Set<Integer> priorities) {
        this.priorities = priorities;
        return this;
    }

    public Set<Integer> getFlags() {
        return flags;
    }

    public CoObjectCriteria setFlags(Set<Integer> flags) {
        this.flags = flags;
        return this;
    }

    public Set<Integer> getStates() {
        return states;
    }

    public CoObjectCriteria setStates(Set<Integer> states) {
        this.states = states;
        return this;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public CoObjectCriteria setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Integer getOwnerGroupId() {
        return ownerGroupId;
    }

    public CoObjectCriteria setOwnerGroupId(Integer ownerGroupId) {
        this.ownerGroupId = ownerGroupId;
        return this;
    }

    public Set<Integer> getAcls() {
        return acls;
    }

    public CoObjectCriteria setAcls(Set<Integer> acls) {
        this.acls = acls;
        return this;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        codeName = map.getString("code", codeName);
        label = map.getString("label", label);
        description = map.getString("description", description);
        queryText = map.getString("q", queryText);
        timeRange = map.getDateRange("times", timeRange);
        priorities = map.getInts("priorities", priorities);
        flags = map.getInts("flags", flags);
        states = map.getInts("states", states);
        ownerId = map.getInt("uid", ownerId);
        ownerGroupId = map.getInt("gid", ownerGroupId);
        acls = map.getInts("acls", acls);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * XXX mybatis global vars won't work.
     */
    public LoginContext getLogin() {
        LoginContext loginContext = LoginContext.fromSession();
        return loginContext;
    }

    static TypeNearby[] nearbies = {
            // TODO cache-enable?
            new TypeNearby(null, null, "Criteria", false), //
            new TypeNearby(null, "impl.", "Criteria", false), //
    };

    public static Class<? extends CoObjectCriteria> findCriteriaClass(Class<?> objClass) {
        Class<? extends CoObjectCriteria> criteriaClass;
        for (TypeNearby nearby : nearbies) {
            criteriaClass = (Class<? extends CoObjectCriteria>) nearby.find(objClass);
            if (criteriaClass != null)
                return criteriaClass;
        }
        return null;
    }

}
