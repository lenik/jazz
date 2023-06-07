package net.bodz.lily.contact.dao;

import java.sql.Date;
import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _PersonMask_stuff
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

    String locale;
    String localePattern;

    String timeZone;
    String timeZonePattern;

    Integer roleCount;
    IntegerRange roleCountRange = new IntegerRange();

    Boolean customer;

    Boolean supplier;

    Boolean employee;

    String subject;
    String subjectPattern;

    Integer bankCount;
    IntegerRange bankCountRange = new IntegerRange();

    String gender;
    String genderPattern;

    String homeland;
    String homelandPattern;

    String passport;
    String passportPattern;

    String ssn;
    String ssnPattern;

    String dln;
    String dlnPattern;

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

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean value) {
        this.customer = value;
    }

    public Boolean getSupplier() {
        return supplier;
    }

    public void setSupplier(Boolean value) {
        this.supplier = value;
    }

    public Boolean getEmployee() {
        return employee;
    }

    public void setEmployee(Boolean value) {
        this.employee = value;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    public String getGenderPattern() {
        return genderPattern;
    }

    public void setGenderPattern(String value) {
        this.genderPattern = value;
    }

    public String getHomeland() {
        return homeland;
    }

    public void setHomeland(String value) {
        this.homeland = value;
    }

    public String getHomelandPattern() {
        return homelandPattern;
    }

    public void setHomelandPattern(String value) {
        this.homelandPattern = value;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String value) {
        this.passport = value;
    }

    public String getPassportPattern() {
        return passportPattern;
    }

    public void setPassportPattern(String value) {
        this.passportPattern = value;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String value) {
        this.ssn = value;
    }

    public String getSsnPattern() {
        return ssnPattern;
    }

    public void setSsnPattern(String value) {
        this.ssnPattern = value;
    }

    public String getDln() {
        return dln;
    }

    public void setDln(String value) {
        this.dln = value;
    }

    public String getDlnPattern() {
        return dlnPattern;
    }

    public void setDlnPattern(String value) {
        this.dlnPattern = value;
    }

}
