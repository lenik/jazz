package net.bodz.lily.vapp.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _VAppRequestMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Integer ownerUserId;
    IntegerRange ownerUserIdRange = new IntegerRange();

    Integer accessMode;
    IntegerRange accessModeRange = new IntegerRange();

    Integer acl;
    IntegerRange aclRange = new IntegerRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    String code;
    String codePattern;

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

    Integer dummy;
    IntegerRange dummyRange = new IntegerRange();

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

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer value) {
        this.ownerUserId = value;
    }

    public IntegerRange getOwnerUserIdRange() {
        return ownerUserIdRange;
    }

    public void setOwnerUserIdRange(IntegerRange range) {
        this.ownerUserIdRange = range;
    }

    public Integer getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(Integer value) {
        this.accessMode = value;
    }

    public IntegerRange getAccessModeRange() {
        return accessModeRange;
    }

    public void setAccessModeRange(IntegerRange range) {
        this.accessModeRange = range;
    }

    public Integer getAcl() {
        return acl;
    }

    public void setAcl(Integer value) {
        this.acl = value;
    }

    public IntegerRange getAclRange() {
        return aclRange;
    }

    public void setAclRange(IntegerRange range) {
        this.aclRange = range;
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

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getCodePattern() {
        return codePattern;
    }

    public void setCodePattern(String value) {
        this.codePattern = value;
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

    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer value) {
        this.dummy = value;
    }

    public IntegerRange getDummyRange() {
        return dummyRange;
    }

    public void setDummyRange(IntegerRange range) {
        this.dummyRange = range;
    }

}
