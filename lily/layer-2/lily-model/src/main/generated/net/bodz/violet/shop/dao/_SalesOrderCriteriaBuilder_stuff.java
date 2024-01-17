package net.bodz.violet.shop.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _SalesOrderCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

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

    Object properties;

    Integer categoryId;
    IntegerRange categoryIdRange = new IntegerRange();

    Integer phaseId;
    IntegerRange phaseIdRange = new IntegerRange();

    Long previousOrderId;
    LongRange previousOrderIdRange = new LongRange();

    Long planId;
    LongRange planIdRange = new LongRange();

    Integer customerOrgId;
    IntegerRange customerOrgIdRange = new IntegerRange();

    Integer customerId;
    IntegerRange customerIdRange = new IntegerRange();

    Integer length;
    IntegerRange lengthRange = new IntegerRange();

    BigDecimal totalQuantity;
    BigDecimalRange totalQuantityRange = new BigDecimalRange();

    BigDecimal totalAmount;
    BigDecimalRange totalAmountRange = new BigDecimalRange();

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

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
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

    public Long getPreviousOrderId() {
        return previousOrderId;
    }

    public void setPreviousOrderId(Long value) {
        this.previousOrderId = value;
    }

    public LongRange getPreviousOrderIdRange() {
        return previousOrderIdRange;
    }

    public void setPreviousOrderIdRange(LongRange range) {
        this.previousOrderIdRange = range;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long value) {
        this.planId = value;
    }

    public LongRange getPlanIdRange() {
        return planIdRange;
    }

    public void setPlanIdRange(LongRange range) {
        this.planIdRange = range;
    }

    public Integer getCustomerOrgId() {
        return customerOrgId;
    }

    public void setCustomerOrgId(Integer value) {
        this.customerOrgId = value;
    }

    public IntegerRange getCustomerOrgIdRange() {
        return customerOrgIdRange;
    }

    public void setCustomerOrgIdRange(IntegerRange range) {
        this.customerOrgIdRange = range;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer value) {
        this.customerId = value;
    }

    public IntegerRange getCustomerIdRange() {
        return customerIdRange;
    }

    public void setCustomerIdRange(IntegerRange range) {
        this.customerIdRange = range;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer value) {
        this.length = value;
    }

    public IntegerRange getLengthRange() {
        return lengthRange;
    }

    public void setLengthRange(IntegerRange range) {
        this.lengthRange = range;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal value) {
        this.totalQuantity = value;
    }

    public BigDecimalRange getTotalQuantityRange() {
        return totalQuantityRange;
    }

    public void setTotalQuantityRange(BigDecimalRange range) {
        this.totalQuantityRange = range;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

    public BigDecimalRange getTotalAmountRange() {
        return totalAmountRange;
    }

    public void setTotalAmountRange(BigDecimalRange range) {
        this.totalAmountRange = range;
    }

}
