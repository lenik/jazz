package net.bodz.lily.pub.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _PostTalkMask_stuff
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

    Timestamp beginTime;
    DateTimeRange beginTimeRange = new DateTimeRange();

    Timestamp endTime;
    DateTimeRange endTimeRange = new DateTimeRange();

    Integer year;
    IntegerRange yearRange = new IntegerRange();

    String subject;
    String subjectPattern;

    Integer opId;
    IntegerRange opIdRange = new IntegerRange();

    String rawText;
    String rawTextPattern;

    Integer formId;
    IntegerRange formIdRange = new IntegerRange();

    String formArguments;
    String formArgumentsPattern;

    Long postId;
    LongRange postIdRange = new LongRange();

    Long parentId;
    LongRange parentIdRange = new LongRange();

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    public String getSubjectPattern() {
        return subjectPattern;
    }

    public void setSubjectPattern(String value) {
        this.subjectPattern = value;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer value) {
        this.opId = value;
    }

    public IntegerRange getOpIdRange() {
        return opIdRange;
    }

    public void setOpIdRange(IntegerRange range) {
        this.opIdRange = range;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String value) {
        this.rawText = value;
    }

    public String getRawTextPattern() {
        return rawTextPattern;
    }

    public void setRawTextPattern(String value) {
        this.rawTextPattern = value;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer value) {
        this.formId = value;
    }

    public IntegerRange getFormIdRange() {
        return formIdRange;
    }

    public void setFormIdRange(IntegerRange range) {
        this.formIdRange = range;
    }

    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    public String getFormArgumentsPattern() {
        return formArgumentsPattern;
    }

    public void setFormArgumentsPattern(String value) {
        this.formArgumentsPattern = value;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long value) {
        this.postId = value;
    }

    public LongRange getPostIdRange() {
        return postIdRange;
    }

    public void setPostIdRange(LongRange range) {
        this.postIdRange = range;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long value) {
        this.parentId = value;
    }

    public LongRange getParentIdRange() {
        return parentIdRange;
    }

    public void setParentIdRange(LongRange range) {
        this.parentIdRange = range;
    }

}
