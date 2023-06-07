package net.bodz.violet.edu.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _TestPaperItemMask_stuff
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

    Object properties;

    Integer paperId;
    IntegerRange paperIdRange = new IntegerRange();

    Long questionId;
    LongRange questionIdRange = new LongRange();

    BigDecimal score;
    BigDecimalRange scoreRange = new BigDecimalRange();

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

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer value) {
        this.paperId = value;
    }

    public IntegerRange getPaperIdRange() {
        return paperIdRange;
    }

    public void setPaperIdRange(IntegerRange range) {
        this.paperIdRange = range;
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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal value) {
        this.score = value;
    }

    public BigDecimalRange getScoreRange() {
        return scoreRange;
    }

    public void setScoreRange(BigDecimalRange range) {
        this.scoreRange = range;
    }

}
