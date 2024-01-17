package net.bodz.lily.vapp.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _VApiCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Object properties;

    Integer appId;
    IntegerRange appIdRange = new IntegerRange();

    Integer apiId;
    IntegerRange apiIdRange = new IntegerRange();

    String callback;
    String callbackPattern;

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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String value) {
        this.callback = value;
    }

    public String getCallbackPattern() {
        return callbackPattern;
    }

    public void setCallbackPattern(String value) {
        this.callbackPattern = value;
    }

}
