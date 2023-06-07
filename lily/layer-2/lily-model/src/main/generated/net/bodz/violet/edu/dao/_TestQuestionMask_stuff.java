package net.bodz.violet.edu.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.t.base.CoMessageMask;

public class _TestQuestionMask_stuff
        extends CoMessageMask {

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

    String subject;
    String subjectPattern;

    String rawText;
    String rawTextPattern;

    String formArguments;
    String formArgumentsPattern;

    Integer courseId;
    IntegerRange courseIdRange = new IntegerRange();

    Object properties;

    Integer favCount;
    IntegerRange favCountRange = new IntegerRange();

    Integer voteCount;
    IntegerRange voteCountRange = new IntegerRange();

    Integer hateCount;
    IntegerRange hateCountRange = new IntegerRange();

    Integer pos;
    IntegerRange posRange = new IntegerRange();

    String answer;
    String answerPattern;

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer value) {
        this.courseId = value;
    }

    public IntegerRange getCourseIdRange() {
        return courseIdRange;
    }

    public void setCourseIdRange(IntegerRange range) {
        this.courseIdRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Integer getFavCount() {
        return favCount;
    }

    public void setFavCount(Integer value) {
        this.favCount = value;
    }

    public IntegerRange getFavCountRange() {
        return favCountRange;
    }

    public void setFavCountRange(IntegerRange range) {
        this.favCountRange = range;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer value) {
        this.voteCount = value;
    }

    public IntegerRange getVoteCountRange() {
        return voteCountRange;
    }

    public void setVoteCountRange(IntegerRange range) {
        this.voteCountRange = range;
    }

    public Integer getHateCount() {
        return hateCount;
    }

    public void setHateCount(Integer value) {
        this.hateCount = value;
    }

    public IntegerRange getHateCountRange() {
        return hateCountRange;
    }

    public void setHateCountRange(IntegerRange range) {
        this.hateCountRange = range;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer value) {
        this.pos = value;
    }

    public IntegerRange getPosRange() {
        return posRange;
    }

    public void setPosRange(IntegerRange range) {
        this.posRange = range;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String value) {
        this.answer = value;
    }

    public String getAnswerPattern() {
        return answerPattern;
    }

    public void setAnswerPattern(String value) {
        this.answerPattern = value;
    }

}
