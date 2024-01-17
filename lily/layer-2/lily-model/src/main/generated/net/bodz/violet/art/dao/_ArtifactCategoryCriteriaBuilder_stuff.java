package net.bodz.violet.art.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.template.CoCategoryCriteriaBuilder;

public class _ArtifactCategoryCriteriaBuilder_stuff
        extends CoCategoryCriteriaBuilder {

    String code;
    String codePattern;

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

    Object properties;

    Integer depth;
    IntegerRange depthRange = new IntegerRange();

    String skufmt;
    String skufmtPattern;

    String skufmtfull;
    String skufmtfullPattern;

    String barfmt;
    String barfmtPattern;

    String barfmtfull;
    String barfmtfullPattern;

    String batchfmt;
    String batchfmtPattern;

    String serialfmt;
    String serialfmtPattern;

    Integer count;
    IntegerRange countRange = new IntegerRange();

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

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer value) {
        this.depth = value;
    }

    public IntegerRange getDepthRange() {
        return depthRange;
    }

    public void setDepthRange(IntegerRange range) {
        this.depthRange = range;
    }

    public String getSkufmt() {
        return skufmt;
    }

    public void setSkufmt(String value) {
        this.skufmt = value;
    }

    public String getSkufmtPattern() {
        return skufmtPattern;
    }

    public void setSkufmtPattern(String value) {
        this.skufmtPattern = value;
    }

    public String getSkufmtfull() {
        return skufmtfull;
    }

    public void setSkufmtfull(String value) {
        this.skufmtfull = value;
    }

    public String getSkufmtfullPattern() {
        return skufmtfullPattern;
    }

    public void setSkufmtfullPattern(String value) {
        this.skufmtfullPattern = value;
    }

    public String getBarfmt() {
        return barfmt;
    }

    public void setBarfmt(String value) {
        this.barfmt = value;
    }

    public String getBarfmtPattern() {
        return barfmtPattern;
    }

    public void setBarfmtPattern(String value) {
        this.barfmtPattern = value;
    }

    public String getBarfmtfull() {
        return barfmtfull;
    }

    public void setBarfmtfull(String value) {
        this.barfmtfull = value;
    }

    public String getBarfmtfullPattern() {
        return barfmtfullPattern;
    }

    public void setBarfmtfullPattern(String value) {
        this.barfmtfullPattern = value;
    }

    public String getBatchfmt() {
        return batchfmt;
    }

    public void setBatchfmt(String value) {
        this.batchfmt = value;
    }

    public String getBatchfmtPattern() {
        return batchfmtPattern;
    }

    public void setBatchfmtPattern(String value) {
        this.batchfmtPattern = value;
    }

    public String getSerialfmt() {
        return serialfmt;
    }

    public void setSerialfmt(String value) {
        this.serialfmt = value;
    }

    public String getSerialfmtPattern() {
        return serialfmtPattern;
    }

    public void setSerialfmtPattern(String value) {
        this.serialfmtPattern = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer value) {
        this.count = value;
    }

    public IntegerRange getCountRange() {
        return countRange;
    }

    public void setCountRange(IntegerRange range) {
        this.countRange = range;
    }

}
