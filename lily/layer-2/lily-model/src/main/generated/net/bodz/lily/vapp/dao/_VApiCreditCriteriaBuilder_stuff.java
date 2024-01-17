package net.bodz.lily.vapp.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _VApiCreditCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

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

    Integer appId;
    IntegerRange appIdRange = new IntegerRange();

    Integer apiId;
    IntegerRange apiIdRange = new IntegerRange();

    BigDecimal credit;
    BigDecimalRange creditRange = new BigDecimalRange();

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

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal value) {
        this.credit = value;
    }

    public BigDecimalRange getCreditRange() {
        return creditRange;
    }

    public void setCreditRange(BigDecimalRange range) {
        this.creditRange = range;
    }

}
