package net.bodz.violet.store.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoNodeCriteriaBuilder;

public class _RegionCriteriaBuilder_stuff
        extends CoNodeCriteriaBuilder {

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

    String path;
    String pathPattern;

    Integer protoId;
    IntegerRange protoIdRange = new IntegerRange();

    Integer depth;
    IntegerRange depthRange = new IntegerRange();

    Integer height;
    IntegerRange heightRange = new IntegerRange();

    Integer levelId;
    IntegerRange levelIdRange = new IntegerRange();

    Integer categoryId;
    IntegerRange categoryIdRange = new IntegerRange();

    Integer tagId;
    IntegerRange tagIdRange = new IntegerRange();

    Integer materialId;
    IntegerRange materialIdRange = new IntegerRange();

    Integer forCatId;
    IntegerRange forCatIdRange = new IntegerRange();

    Integer forArtId;
    IntegerRange forArtIdRange = new IntegerRange();

    Integer artifactId;
    IntegerRange artifactIdRange = new IntegerRange();

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

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        this.path = value;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public void setPathPattern(String value) {
        this.pathPattern = value;
    }

    public Integer getProtoId() {
        return protoId;
    }

    public void setProtoId(Integer value) {
        this.protoId = value;
    }

    public IntegerRange getProtoIdRange() {
        return protoIdRange;
    }

    public void setProtoIdRange(IntegerRange range) {
        this.protoIdRange = range;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer value) {
        this.height = value;
    }

    public IntegerRange getHeightRange() {
        return heightRange;
    }

    public void setHeightRange(IntegerRange range) {
        this.heightRange = range;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer value) {
        this.levelId = value;
    }

    public IntegerRange getLevelIdRange() {
        return levelIdRange;
    }

    public void setLevelIdRange(IntegerRange range) {
        this.levelIdRange = range;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    public IntegerRange getCategoryIdRange() {
        return categoryIdRange;
    }

    public void setCategoryIdRange(IntegerRange range) {
        this.categoryIdRange = range;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer value) {
        this.tagId = value;
    }

    public IntegerRange getTagIdRange() {
        return tagIdRange;
    }

    public void setTagIdRange(IntegerRange range) {
        this.tagIdRange = range;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer value) {
        this.materialId = value;
    }

    public IntegerRange getMaterialIdRange() {
        return materialIdRange;
    }

    public void setMaterialIdRange(IntegerRange range) {
        this.materialIdRange = range;
    }

    public Integer getForCatId() {
        return forCatId;
    }

    public void setForCatId(Integer value) {
        this.forCatId = value;
    }

    public IntegerRange getForCatIdRange() {
        return forCatIdRange;
    }

    public void setForCatIdRange(IntegerRange range) {
        this.forCatIdRange = range;
    }

    public Integer getForArtId() {
        return forArtId;
    }

    public void setForArtId(Integer value) {
        this.forArtId = value;
    }

    public IntegerRange getForArtIdRange() {
        return forArtIdRange;
    }

    public void setForArtIdRange(IntegerRange range) {
        this.forArtIdRange = range;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer value) {
        this.artifactId = value;
    }

    public IntegerRange getArtifactIdRange() {
        return artifactIdRange;
    }

    public void setArtifactIdRange(IntegerRange range) {
        this.artifactIdRange = range;
    }

}
