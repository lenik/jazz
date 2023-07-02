package net.bodz.lily.contact.dao;

import java.sql.Date;
import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _OrganizationMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

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

    Integer categoryId;
    IntegerRange categoryIdRange = new IntegerRange();

    Date birthday;
    DateTimeRange birthdayRange = new DateTimeRange();

    String langTag;
    String langTagPattern;

    String timeZone;
    String timeZonePattern;

    Integer roleCount;
    IntegerRange roleCountRange = new IntegerRange();

    Boolean supplier;

    Boolean customer;

    String subject;
    String subjectPattern;

    Integer bankCount;
    IntegerRange bankCountRange = new IntegerRange();

    Integer size;
    IntegerRange sizeRange = new IntegerRange();

    String taxId;
    String taxIdPattern;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date value) {
        this.birthday = value;
    }

    public DateTimeRange getBirthdayRange() {
        return birthdayRange;
    }

    public void setBirthdayRange(DateTimeRange range) {
        this.birthdayRange = range;
    }

    public String getLangTag() {
        return langTag;
    }

    public void setLangTag(String value) {
        this.langTag = value;
    }

    public String getLangTagPattern() {
        return langTagPattern;
    }

    public void setLangTagPattern(String value) {
        this.langTagPattern = value;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    public String getTimeZonePattern() {
        return timeZonePattern;
    }

    public void setTimeZonePattern(String value) {
        this.timeZonePattern = value;
    }

    public Integer getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(Integer value) {
        this.roleCount = value;
    }

    public IntegerRange getRoleCountRange() {
        return roleCountRange;
    }

    public void setRoleCountRange(IntegerRange range) {
        this.roleCountRange = range;
    }

    public Boolean getSupplier() {
        return supplier;
    }

    public void setSupplier(Boolean value) {
        this.supplier = value;
    }

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean value) {
        this.customer = value;
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

    public Integer getBankCount() {
        return bankCount;
    }

    public void setBankCount(Integer value) {
        this.bankCount = value;
    }

    public IntegerRange getBankCountRange() {
        return bankCountRange;
    }

    public void setBankCountRange(IntegerRange range) {
        this.bankCountRange = range;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer value) {
        this.size = value;
    }

    public IntegerRange getSizeRange() {
        return sizeRange;
    }

    public void setSizeRange(IntegerRange range) {
        this.sizeRange = range;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String value) {
        this.taxId = value;
    }

    public String getTaxIdPattern() {
        return taxIdPattern;
    }

    public void setTaxIdPattern(String value) {
        this.taxIdPattern = value;
    }

}
