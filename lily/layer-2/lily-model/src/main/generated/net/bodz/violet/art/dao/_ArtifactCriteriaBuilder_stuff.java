package net.bodz.violet.art.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.ShortRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ArtifactCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    String skuCode;
    String skuCodePattern;

    String barCode;
    String barCodePattern;

    String rfidCode;
    String rfidCodePattern;

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

    String modelName;
    String modelNamePattern;

    Integer protoId;
    IntegerRange protoIdRange = new IntegerRange();

    Integer categoryId;
    IntegerRange categoryIdRange = new IntegerRange();

    Integer phaseId;
    IntegerRange phaseIdRange = new IntegerRange();

    Integer uomId;
    IntegerRange uomIdRange = new IntegerRange();

    Object properties;

    Short finish;
    ShortRange finishRange = new ShortRange();

    BigDecimal price;
    BigDecimalRange priceRange = new BigDecimalRange();

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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String value) {
        this.skuCode = value;
    }

    public String getSkuCodePattern() {
        return skuCodePattern;
    }

    public void setSkuCodePattern(String value) {
        this.skuCodePattern = value;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String value) {
        this.barCode = value;
    }

    public String getBarCodePattern() {
        return barCodePattern;
    }

    public void setBarCodePattern(String value) {
        this.barCodePattern = value;
    }

    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String value) {
        this.rfidCode = value;
    }

    public String getRfidCodePattern() {
        return rfidCodePattern;
    }

    public void setRfidCodePattern(String value) {
        this.rfidCodePattern = value;
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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String value) {
        this.modelName = value;
    }

    public String getModelNamePattern() {
        return modelNamePattern;
    }

    public void setModelNamePattern(String value) {
        this.modelNamePattern = value;
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

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer value) {
        this.phaseId = value;
    }

    public IntegerRange getPhaseIdRange() {
        return phaseIdRange;
    }

    public void setPhaseIdRange(IntegerRange range) {
        this.phaseIdRange = range;
    }

    public Integer getUomId() {
        return uomId;
    }

    public void setUomId(Integer value) {
        this.uomId = value;
    }

    public IntegerRange getUomIdRange() {
        return uomIdRange;
    }

    public void setUomIdRange(IntegerRange range) {
        this.uomIdRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Short getFinish() {
        return finish;
    }

    public void setFinish(Short value) {
        this.finish = value;
    }

    public ShortRange getFinishRange() {
        return finishRange;
    }

    public void setFinishRange(ShortRange range) {
        this.finishRange = range;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    public BigDecimalRange getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(BigDecimalRange range) {
        this.priceRange = range;
    }

}
