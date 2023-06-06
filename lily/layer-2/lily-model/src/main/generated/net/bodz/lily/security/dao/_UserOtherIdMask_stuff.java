package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _UserOtherIdMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Timestamp beginTime;
    DateTimeRange beginTimeRange = new DateTimeRange();

    Timestamp endTime;
    DateTimeRange endTimeRange = new DateTimeRange();

    Integer year;
    IntegerRange yearRange = new IntegerRange();

    Object properties;

    /** The declaring user */
    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

    /** Type of Open ID */
    Integer typeId;
    IntegerRange typeIdRange = new IntegerRange();

    /** The identity data */
    String oid;
    String oidPattern;

    /** The authentication data */
    Object auth;

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

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp value) {
        this.beginTime = value;
    }

    public DateTimeRange getBeginTimeRange() {
        return beginTimeRange;
    }

    public void setBeginTimeRange(DateTimeRange range) {
        this.beginTimeRange = range;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp value) {
        this.endTime = value;
    }

    public DateTimeRange getEndTimeRange() {
        return endTimeRange;
    }

    public void setEndTimeRange(DateTimeRange range) {
        this.endTimeRange = range;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer value) {
        this.year = value;
    }

    public IntegerRange getYearRange() {
        return yearRange;
    }

    public void setYearRange(IntegerRange range) {
        this.yearRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    /** The declaring user */
    public Integer getUserId() {
        return userId;
    }

    /** The declaring user */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public IntegerRange getUserIdRange() {
        return userIdRange;
    }

    public void setUserIdRange(IntegerRange range) {
        this.userIdRange = range;
    }

    /** Type of Open ID */
    public Integer getTypeId() {
        return typeId;
    }

    /** Type of Open ID */
    public void setTypeId(Integer value) {
        this.typeId = value;
    }

    public IntegerRange getTypeIdRange() {
        return typeIdRange;
    }

    public void setTypeIdRange(IntegerRange range) {
        this.typeIdRange = range;
    }

    /** The identity data */
    public String getOid() {
        return oid;
    }

    /** The identity data */
    public void setOid(String value) {
        this.oid = value;
    }

    public String getOidPattern() {
        return oidPattern;
    }

    public void setOidPattern(String value) {
        this.oidPattern = value;
    }

    /** The authentication data */
    public Object getAuth() {
        return auth;
    }

    /** The authentication data */
    public void setAuth(Object value) {
        this.auth = value;
    }

}
