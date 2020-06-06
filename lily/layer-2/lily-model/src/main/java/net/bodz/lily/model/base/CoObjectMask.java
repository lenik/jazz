package net.bodz.lily.model.base;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.bodz.bas.c.type.NameConventionTypeMapper;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VarMapLoader;
import net.bodz.lily.security.login.LoginToken;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class CoObjectMask
        implements IVarMapSerializable {

    final LongRange idRange = new LongRange();

    String codeName;
    String label;
    String description;

    String queryText;

    DateTimeRange creation = new DateTimeRange();
    DateTimeRange lastmod = new DateTimeRange();

    Set<Integer> priorities;
    Set<Integer> flags;
    Integer state;
    Set<Integer> states;
    Integer ownerId;
    Integer ownerGroupId;
    Set<Integer> acls;

    public LongRange getIdRange() {
        return idRange;
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

    public DateTimeRange getCreation() {
        return creation;
    }

    public void setCreation(DateTimeRange creation) {
        this.creation = creation;
    }

    public DateTimeRange getLastmod() {
        return lastmod;
    }

    public void setLastmod(DateTimeRange lastmod) {
        this.lastmod = lastmod;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<Integer> getStates() {
        return states;
    }

    public CoObjectMask setStates(int... states) {
        this.states = new HashSet<>();
        for (int state : states)
            this.states.add(state);
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
    public void readObject(IVariantMap<String> map)
            throws LoaderException {
        VarMapLoader loader = new VarMapLoader();
        loader.load(getClass(), this, map);

        codeName = map.getString("code", codeName);
        queryText = map.getString("q", queryText);
        ownerId = map.getInt("uid", ownerId);
        ownerGroupId = map.getInt("gid", ownerGroupId);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        // TODO Use reflection to write.
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * XXX mybatis global vars won't work.
     */
    public LoginToken getLoginToken() {
        LoginToken token = LoginToken.fromSession();
        return token;
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
