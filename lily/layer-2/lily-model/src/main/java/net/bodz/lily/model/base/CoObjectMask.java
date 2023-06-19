package net.bodz.lily.model.base;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.RangeVarsFn;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VarMapLoader;
import net.bodz.lily.security.login.LoginToken;

/**
 * @see net.bodz.lily.model.base.CoObject
 */
public class CoObjectMask
        extends StructRowMask {

//    Range idRange = new LongRange();

    String uniqName;
    String uniqNamePattern;

    String label;
    String labelPattern;

    String description;
    String descriptionPattern;

    String queryText;

    DateTimeRange creation = new DateTimeRange();
    DateTimeRange lastmod = new DateTimeRange();

    Set<Integer> priorities;
    Set<Integer> flags;
    IntegerRange stateRange;
    Set<Integer> states;
    IntegerRange ownerIdRange;
    IntegerRange ownerGroupIdRange;
    Set<Integer> acls;

//    public Range getIdRange() {
//        return idRange;
//    }
//
//    public void setIdRange(Range idRange) {
//        this.idRange = idRange;
//    }

    public String getUniqName() {
        return uniqName;
    }

    public void setUniqName(String uniqName) {
        this.uniqName = uniqName;
    }

    public String getUniqNamePattern() {
        return uniqNamePattern;
    }

    public void setUniqNamePattern(String uniqNamePattern) {
        this.uniqNamePattern = uniqNamePattern;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelPattern() {
        return labelPattern;
    }

    public void setLabelPattern(String labelPattern) {
        this.labelPattern = labelPattern;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPattern() {
        return descriptionPattern;
    }

    public void setDescriptionPattern(String descriptionPattern) {
        this.descriptionPattern = descriptionPattern;
    }

    public String getQueryText() {
        return queryText;
    }

    public CoObjectMask setQueryText(String queryText) {
        this.queryText = queryText;
        return this;
    }

    @Override
    public DateTimeRange getCreation() {
        return creation;
    }

    @Override
    public void setCreation(DateTimeRange creation) {
        this.creation = creation;
    }

    @Override
    public DateTimeRange getLastmod() {
        return lastmod;
    }

    @Override
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
        return stateRange == null ? null : stateRange.getPointValue();
    }

    public void setState(Integer state) {
        this.stateRange = state == null ? null : new IntegerRange().point(state);
    }

    public IntegerRange getStateRange() {
        return stateRange;
    }

    public void setStateRange(IntegerRange stateRange) {
        this.stateRange = stateRange;
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
        return ownerIdRange == null ? null : ownerIdRange.getPointValue();
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerIdRange = ownerId == null ? null : new IntegerRange().point(ownerId);
    }

    public IntegerRange getOwnerIdRange() {
        return ownerIdRange;
    }

    public void setOwnerIdRange(IntegerRange ownerIdRange) {
        this.ownerIdRange = ownerIdRange;
    }

    public Integer getOwnerGroupId() {
        return ownerGroupIdRange == null ? null : ownerGroupIdRange.getPointValue();
    }

    public void setOwnerGroupId(Integer ownerGroupId) {
        this.ownerGroupIdRange = ownerGroupId == null ? null : new IntegerRange().point(ownerGroupId);
    }

    public IntegerRange getOwnerGroupIdRange() {
        return ownerGroupIdRange;
    }

    public void setOwnerGroupIdRange(IntegerRange ownerGroupIdRange) {
        this.ownerGroupIdRange = ownerGroupIdRange;
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
            throws LoaderException, ParseException {
        VarMapLoader loader = new VarMapLoader();
        loader.load(getClass(), this, map);

        uniqName = map.getString("code", uniqName);
        queryText = map.getString("q", queryText);
        ownerIdRange = RangeVarsFn.getFrom(map, "uid", ownerIdRange);
        ownerGroupIdRange = RangeVarsFn.getFrom(map, "gid", ownerGroupIdRange);
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

    // workarounds..

    IntegerRange flagsRange;

    public IntegerRange getFlagsRange() {
        return flagsRange;
    }

    public void setFlagsRange(IntegerRange flagsRange) {
        this.flagsRange = flagsRange;
    }

}
