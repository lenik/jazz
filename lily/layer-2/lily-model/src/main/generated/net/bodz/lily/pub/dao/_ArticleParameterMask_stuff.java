package net.bodz.lily.pub.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _ArticleParameterMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Long articleId;
    LongRange articleIdRange = new LongRange();

    Integer parameterId;
    IntegerRange parameterIdRange = new IntegerRange();

    Integer ival;
    IntegerRange ivalRange = new IntegerRange();

    Double fval;
    DoubleRange fvalRange = new DoubleRange();

    String sval;
    String svalPattern;

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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long value) {
        this.articleId = value;
    }

    public LongRange getArticleIdRange() {
        return articleIdRange;
    }

    public void setArticleIdRange(LongRange range) {
        this.articleIdRange = range;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer value) {
        this.parameterId = value;
    }

    public IntegerRange getParameterIdRange() {
        return parameterIdRange;
    }

    public void setParameterIdRange(IntegerRange range) {
        this.parameterIdRange = range;
    }

    public Integer getIval() {
        return ival;
    }

    public void setIval(Integer value) {
        this.ival = value;
    }

    public IntegerRange getIvalRange() {
        return ivalRange;
    }

    public void setIvalRange(IntegerRange range) {
        this.ivalRange = range;
    }

    public Double getFval() {
        return fval;
    }

    public void setFval(Double value) {
        this.fval = value;
    }

    public DoubleRange getFvalRange() {
        return fvalRange;
    }

    public void setFvalRange(DoubleRange range) {
        this.fvalRange = range;
    }

    public String getSval() {
        return sval;
    }

    public void setSval(String value) {
        this.sval = value;
    }

    public String getSvalPattern() {
        return svalPattern;
    }

    public void setSvalPattern(String value) {
        this.svalPattern = value;
    }

}
