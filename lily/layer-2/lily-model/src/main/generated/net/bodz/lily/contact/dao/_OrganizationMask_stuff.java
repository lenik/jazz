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

    String alias;
    String aliasPattern;

    Object contactProperties;

    String address1;
    String address1Pattern;

    String address2;
    String address2Pattern;

    Integer zoneId;
    IntegerRange zoneIdRange = new IntegerRange();

    String tel;
    String telPattern;

    Boolean telok;

    String email;
    String emailPattern;

    Boolean emailok;

    Object properties;

    Integer categoryId;
    IntegerRange categoryIdRange = new IntegerRange();

    Date birthday;
    DateTimeRange birthdayRange = new DateTimeRange();

    String locale;
    String localePattern;

    String timezone;
    String timezonePattern;

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

    String taxid;
    String taxidPattern;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String value) {
        this.alias = value;
    }

    public String getAliasPattern() {
        return aliasPattern;
    }

    public void setAliasPattern(String value) {
        this.aliasPattern = value;
    }

    public Object getContactProperties() {
        return contactProperties;
    }

    public void setContactProperties(Object value) {
        this.contactProperties = value;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String value) {
        this.address1 = value;
    }

    public String getAddress1Pattern() {
        return address1Pattern;
    }

    public void setAddress1Pattern(String value) {
        this.address1Pattern = value;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String value) {
        this.address2 = value;
    }

    public String getAddress2Pattern() {
        return address2Pattern;
    }

    public void setAddress2Pattern(String value) {
        this.address2Pattern = value;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer value) {
        this.zoneId = value;
    }

    public IntegerRange getZoneIdRange() {
        return zoneIdRange;
    }

    public void setZoneIdRange(IntegerRange range) {
        this.zoneIdRange = range;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String value) {
        this.tel = value;
    }

    public String getTelPattern() {
        return telPattern;
    }

    public void setTelPattern(String value) {
        this.telPattern = value;
    }

    public Boolean getTelok() {
        return telok;
    }

    public void setTelok(Boolean value) {
        this.telok = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmailPattern() {
        return emailPattern;
    }

    public void setEmailPattern(String value) {
        this.emailPattern = value;
    }

    public Boolean getEmailok() {
        return emailok;
    }

    public void setEmailok(Boolean value) {
        this.emailok = value;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String value) {
        this.locale = value;
    }

    public String getLocalePattern() {
        return localePattern;
    }

    public void setLocalePattern(String value) {
        this.localePattern = value;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String value) {
        this.timezone = value;
    }

    public String getTimezonePattern() {
        return timezonePattern;
    }

    public void setTimezonePattern(String value) {
        this.timezonePattern = value;
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

    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String value) {
        this.taxid = value;
    }

    public String getTaxidPattern() {
        return taxidPattern;
    }

    public void setTaxidPattern(String value) {
        this.taxidPattern = value;
    }

}
