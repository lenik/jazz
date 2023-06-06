package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _UserRunMask_stuff
        extends CoObjectMask {

    /** The user */
    Integer user;
    IntegerRange userRange = new IntegerRange();

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
    Timestamp lastlog;
    DateTimeRange lastlogRange = new DateTimeRange();

    /** The source IP of last login */
    Object lastlogip;

    /** The user */
    public Integer getUser() {
        return user;
    }

    /** The user */
    public void setUser(Integer value) {
        this.user = value;
    }

    public IntegerRange getUserRange() {
        return userRange;
    }

    public void setUserRange(IntegerRange range) {
        this.userRange = range;
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
    public Timestamp getLastlog() {
        return lastlog;
    }

    /** Last time of login */
    public void setLastlog(Timestamp value) {
        this.lastlog = value;
    }

    public DateTimeRange getLastlogRange() {
        return lastlogRange;
    }

    public void setLastlogRange(DateTimeRange range) {
        this.lastlogRange = range;
    }

    /** The source IP of last login */
    public Object getLastlogip() {
        return lastlogip;
    }

    /** The source IP of last login */
    public void setLastlogip(Object value) {
        this.lastlogip = value;
    }

}
