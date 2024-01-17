package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _UserRunCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    /** The user */
    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Object properties;

    Integer score;
    IntegerRange scoreRange = new IntegerRange();

    /** Last time of login */
    Timestamp lastLoginTime;
    DateTimeRange lastLoginTimeRange = new DateTimeRange();

    /** The source IP of last login */
    Object lastLoginIP;

    /** The user */
    public Integer getUserId() {
        return userId;
    }

    /** The user */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public IntegerRange getUserIdRange() {
        return userIdRange;
    }

    public void setUserIdRange(IntegerRange range) {
        this.userIdRange = range;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer value) {
        this.score = value;
    }

    public IntegerRange getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(IntegerRange range) {
        this.scoreRange = range;
    }

    /** Last time of login */
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    /** Last time of login */
    public void setLastLoginTime(Timestamp value) {
        this.lastLoginTime = value;
    }

    public DateTimeRange getLastLoginTimeRange() {
        return lastLoginTimeRange;
    }

    public void setLastLoginTimeRange(DateTimeRange range) {
        this.lastLoginTimeRange = range;
    }

    /** The source IP of last login */
    public Object getLastLoginIP() {
        return lastLoginIP;
    }

    /** The source IP of last login */
    public void setLastLoginIP(Object value) {
        this.lastLoginIP = value;
    }

}
