package net.bodz.lily.vapp.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _VApiLogMask_stuff
        extends CoObjectMask {

    Long id;
    LongRange idRange = new LongRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Integer appId;
    IntegerRange appIdRange = new IntegerRange();

    Integer apiId;
    IntegerRange apiIdRange = new IntegerRange();

    String message;
    String messagePattern;

    String err;
    String errPattern;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public LongRange getIdRange() {
        return idRange;
    }

    public void setIdRange(LongRange range) {
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

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer value) {
        this.appId = value;
    }

    public IntegerRange getAppIdRange() {
        return appIdRange;
    }

    public void setAppIdRange(IntegerRange range) {
        this.appIdRange = range;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer value) {
        this.apiId = value;
    }

    public IntegerRange getApiIdRange() {
        return apiIdRange;
    }

    public void setApiIdRange(IntegerRange range) {
        this.apiIdRange = range;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    public String getMessagePattern() {
        return messagePattern;
    }

    public void setMessagePattern(String value) {
        this.messagePattern = value;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String value) {
        this.err = value;
    }

    public String getErrPattern() {
        return errPattern;
    }

    public void setErrPattern(String value) {
        this.errPattern = value;
    }

}
