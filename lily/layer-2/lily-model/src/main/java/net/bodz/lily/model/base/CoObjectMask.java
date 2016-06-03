package net.bodz.lily.model.base;

import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.bodz.bas.c.type.NameConventionTypeMapper;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.QVariantMap;
import net.bodz.lily.model.base.security.LoginData;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class CoObjectMask
        implements IVarMapSerializable {

    Long pageOffset;
    Long pageLimit;

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

    public Long getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Long pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Long getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Long pageLimit) {
        this.pageLimit = pageLimit;
    }

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

    public CoObjectMask setQueryText(String queryText) {
        this.queryText = queryText;
        return this;
    }

    public DateRange getTimeRange() {
        return timeRange;
    }

    public CoObjectMask setTimeRange(DateRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public Set<Integer> getPriorities() {
        return priorities;
    }

    public CoObjectMask setPriorities(Set<Integer> priorities) {
        this.priorities = priorities;
        return this;
    }

    public Set<Integer> getFlags() {
        return flags;
    }

    public CoObjectMask setFlags(Set<Integer> flags) {
        this.flags = flags;
        return this;
    }

    public Set<Integer> getStates() {
        return states;
    }

    public CoObjectMask setStates(Set<Integer> states) {
        this.states = states;
        return this;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public CoObjectMask setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Integer getOwnerGroupId() {
        return ownerGroupId;
    }

    public CoObjectMask setOwnerGroupId(Integer ownerGroupId) {
        this.ownerGroupId = ownerGroupId;
        return this;
    }

    public Set<Integer> getAcls() {
        return acls;
    }

    public CoObjectMask setAcls(Set<Integer> acls) {
        this.acls = acls;
        return this;
    }

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        // TODO Use reflection to read.
        QVariantMap<String> map = QVariantMap.from(_map);
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
    public void writeObject(IVariantMap<String> map) {
        // TODO Use reflection to write.
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * XXX mybatis global vars won't work.
     */
    public LoginData getLogin() {
        LoginData loginData = LoginData.fromSession();
        return loginData;
    }

    static NameConventionTypeMapper[] maskTmaps = {
            // TODO cache-enable?
            new NameConventionTypeMapper(null, "Mask", false), //
            new NameConventionTypeMapper("impl.", "Mask", false), //
            new NameConventionTypeMapper(null, 1, "db.", "Mask", false), //
    };

    public static Class<? extends CoObjectMask> findMaskClass(Class<?> objClass) {
        Class<? extends CoObjectMask> maskClass;
        for (NameConventionTypeMapper tmap : maskTmaps) {
            maskClass = (Class<? extends CoObjectMask>) tmap.map(objClass);
            if (maskClass != null)
                return maskClass;
        }
        return null;
    }

}
