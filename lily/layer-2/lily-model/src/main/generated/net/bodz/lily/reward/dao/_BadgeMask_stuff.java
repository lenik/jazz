package net.bodz.lily.reward.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _BadgeMask_stuff
        extends CoObjectMask {

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

    String expr;
    String exprPattern;

    Integer val;
    IntegerRange valRange = new IntegerRange();

    int[] levels;

    Boolean descend;

    Boolean transient_;

    Boolean indexed;

    String image;
    String imagePattern;

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

    public String getExpr() {
        return expr;
    }

    public void setExpr(String value) {
        this.expr = value;
    }

    public String getExprPattern() {
        return exprPattern;
    }

    public void setExprPattern(String value) {
        this.exprPattern = value;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer value) {
        this.val = value;
    }

    public IntegerRange getValRange() {
        return valRange;
    }

    public void setValRange(IntegerRange range) {
        this.valRange = range;
    }

    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] value) {
        this.levels = value;
    }

    public Boolean getDescend() {
        return descend;
    }

    public void setDescend(Boolean value) {
        this.descend = value;
    }

    public Boolean getTransient_() {
        return transient_;
    }

    public void setTransient_(Boolean value) {
        this.transient_ = value;
    }

    public Boolean getIndexed() {
        return indexed;
    }

    public void setIndexed(Boolean value) {
        this.indexed = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public String getImagePattern() {
        return imagePattern;
    }

    public void setImagePattern(String value) {
        this.imagePattern = value;
    }

}
