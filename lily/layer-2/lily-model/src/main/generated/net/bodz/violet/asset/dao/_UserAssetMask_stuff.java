package net.bodz.violet.asset.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoMomentIntervalMask;

public class _UserAssetMask_stuff
        extends CoMomentIntervalMask {

    Long id;
    LongRange idRange = new LongRange();

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

    Integer artifactId;
    IntegerRange artifactIdRange = new IntegerRange();

    Integer regionId;
    IntegerRange regionIdRange = new IntegerRange();

    Object batch;

    BigDecimal quantity;
    BigDecimalRange quantityRange = new BigDecimalRange();

    Long serial;
    LongRange serialRange = new LongRange();

    Timestamp expire;
    DateTimeRange expireRange = new DateTimeRange();

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer value) {
        this.regionId = value;
    }

    public IntegerRange getRegionIdRange() {
        return regionIdRange;
    }

    public void setRegionIdRange(IntegerRange range) {
        this.regionIdRange = range;
    }

    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal value) {
        this.quantity = value;
    }

    public BigDecimalRange getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(BigDecimalRange range) {
        this.quantityRange = range;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long value) {
        this.serial = value;
    }

    public LongRange getSerialRange() {
        return serialRange;
    }

    public void setSerialRange(LongRange range) {
        this.serialRange = range;
    }

    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp value) {
        this.expire = value;
    }

    public DateTimeRange getExpireRange() {
        return expireRange;
    }

    public void setExpireRange(DateTimeRange range) {
        this.expireRange = range;
    }

}
