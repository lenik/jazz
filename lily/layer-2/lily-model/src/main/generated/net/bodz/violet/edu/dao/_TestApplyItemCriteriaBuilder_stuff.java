package net.bodz.violet.edu.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _TestApplyItemCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

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

    Long applyId;
    LongRange applyIdRange = new LongRange();

    Long questionId;
    LongRange questionIdRange = new LongRange();

    Integer answer;
    IntegerRange answerRange = new IntegerRange();

    String anstext;
    String anstextPattern;

    Double score;
    DoubleRange scoreRange = new DoubleRange();

    Double waittime;
    DoubleRange waittimeRange = new DoubleRange();

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

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long value) {
        this.applyId = value;
    }

    public LongRange getApplyIdRange() {
        return applyIdRange;
    }

    public void setApplyIdRange(LongRange range) {
        this.applyIdRange = range;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long value) {
        this.questionId = value;
    }

    public LongRange getQuestionIdRange() {
        return questionIdRange;
    }

    public void setQuestionIdRange(LongRange range) {
        this.questionIdRange = range;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer value) {
        this.answer = value;
    }

    public IntegerRange getAnswerRange() {
        return answerRange;
    }

    public void setAnswerRange(IntegerRange range) {
        this.answerRange = range;
    }

    public String getAnstext() {
        return anstext;
    }

    public void setAnstext(String value) {
        this.anstext = value;
    }

    public String getAnstextPattern() {
        return anstextPattern;
    }

    public void setAnstextPattern(String value) {
        this.anstextPattern = value;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double value) {
        this.score = value;
    }

    public DoubleRange getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(DoubleRange range) {
        this.scoreRange = range;
    }

    public Double getWaittime() {
        return waittime;
    }

    public void setWaittime(Double value) {
        this.waittime = value;
    }

    public DoubleRange getWaittimeRange() {
        return waittimeRange;
    }

    public void setWaittimeRange(DoubleRange range) {
        this.waittimeRange = range;
    }

}
