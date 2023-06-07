package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _UserMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    /** User type like system-user, normal-user, etc. */
    Integer typeId;
    IntegerRange typeIdRange = new IntegerRange();

    /** The user name (unique) */
    String uniqName;
    String uniqNamePattern;

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Object properties;

    /** The primary user group, the default value of ownerGroup. */
    Integer primaryGroupId;
    IntegerRange primaryGroupIdRange = new IntegerRange();

    /** The referer user (used for promotion) */
    Integer refererId;
    IntegerRange refererIdRange = new IntegerRange();

    Integer personId;
    IntegerRange personIdRange = new IntegerRange();

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setIdRange(IntegerRange range) {
        this.idRange = range;
    }

    /** User type like system-user, normal-user, etc. */
    public Integer getTypeId() {
        return typeId;
    }

    /** User type like system-user, normal-user, etc. */
    public void setTypeId(Integer value) {
        this.typeId = value;
    }

    public IntegerRange getTypeIdRange() {
        return typeIdRange;
    }

    public void setTypeIdRange(IntegerRange range) {
        this.typeIdRange = range;
    }

    /** The user name (unique) */
    public String getUniqName() {
        return uniqName;
    }

    /** The user name (unique) */
    public void setUniqName(String value) {
        this.uniqName = value;
    }

    public String getUniqNamePattern() {
        return uniqNamePattern;
    }

    public void setUniqNamePattern(String value) {
        this.uniqNamePattern = value;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer value) {
        this.priority = value;
    }

    public IntegerRange getPriorityRange() {
        return priorityRange;
    }

    public void setPriorityRange(IntegerRange range) {
        this.priorityRange = range;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp value) {
        this.creationDate = value;
    }

    public DateTimeRange getCreationDateRange() {
        return creationDateRange;
    }

    public void setCreationDateRange(DateTimeRange range) {
        this.creationDateRange = range;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp value) {
        this.lastModifiedDate = value;
    }

    public DateTimeRange getLastModifiedDateRange() {
        return lastModifiedDateRange;
    }

    public void setLastModifiedDateRange(DateTimeRange range) {
        this.lastModifiedDateRange = range;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer value) {
        this.version = value;
    }

    public IntegerRange getVersionRange() {
        return versionRange;
    }

    public void setVersionRange(IntegerRange range) {
        this.versionRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    /** The primary user group, the default value of ownerGroup. */
    public Integer getPrimaryGroupId() {
        return primaryGroupId;
    }

    /** The primary user group, the default value of ownerGroup. */
    public void setPrimaryGroupId(Integer value) {
        this.primaryGroupId = value;
    }

    public IntegerRange getPrimaryGroupIdRange() {
        return primaryGroupIdRange;
    }

    public void setPrimaryGroupIdRange(IntegerRange range) {
        this.primaryGroupIdRange = range;
    }

    /** The referer user (used for promotion) */
    public Integer getRefererId() {
        return refererId;
    }

    /** The referer user (used for promotion) */
    public void setRefererId(Integer value) {
        this.refererId = value;
    }

    public IntegerRange getRefererIdRange() {
        return refererIdRange;
    }

    public void setRefererIdRange(IntegerRange range) {
        this.refererIdRange = range;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer value) {
        this.personId = value;
    }

    public IntegerRange getPersonIdRange() {
        return personIdRange;
    }

    public void setPersonIdRange(IntegerRange range) {
        this.personIdRange = range;
    }

}
