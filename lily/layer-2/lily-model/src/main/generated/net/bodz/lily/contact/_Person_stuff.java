package net.bodz.lily.contact;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.geo.Zone;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _Person_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_ALIAS = 32;
    public static final int N_ADDRESS1 = 80;
    public static final int N_ADDRESS2 = 80;
    public static final int N_ZONE_ID = 10;
    public static final int N_TEL = 20;
    public static final int N_EMAIL = 30;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_LOCALE = 10;
    public static final int N_TIMEZONE = 40;
    public static final int N_SUBJECT = 200;
    public static final int N_GENDER = 1;
    public static final int N_HOMELAND = 10;
    public static final int N_PASSPORT = 20;
    public static final int N_SSN = 20;
    public static final int N_DLN = 20;

    private static final int _ord_ID = 1;
    private static final int _ord_ALIAS = 14;
    private static final int _ord_CONTACT_PROPERTIES = _ord_ALIAS + 1;
    private static final int _ord_ADDRESS1 = _ord_CONTACT_PROPERTIES + 1;
    private static final int _ord_ADDRESS2 = _ord_ADDRESS1 + 1;
    private static final int _ord_ZONE_ID = _ord_ADDRESS2 + 1;
    private static final int _ord_TEL = _ord_ZONE_ID + 1;
    private static final int _ord_TELOK = _ord_TEL + 1;
    private static final int _ord_EMAIL = _ord_TELOK + 1;
    private static final int _ord_EMAILOK = _ord_EMAIL + 1;
    private static final int _ord_CATEGORY_ID = _ord_EMAILOK + 2;
    private static final int _ord_BIRTHDAY = _ord_CATEGORY_ID + 1;
    private static final int _ord_LOCALE = _ord_BIRTHDAY + 1;
    private static final int _ord_TIMEZONE = _ord_LOCALE + 1;
    private static final int _ord_ROLE_COUNT = _ord_TIMEZONE + 1;
    private static final int _ord_CUSTOMER = _ord_ROLE_COUNT + 1;
    private static final int _ord_SUPPLIER = _ord_CUSTOMER + 1;
    private static final int _ord_EMPLOYEE = _ord_SUPPLIER + 1;
    private static final int _ord_SUBJECT = _ord_EMPLOYEE + 1;
    private static final int _ord_BANK_COUNT = _ord_SUBJECT + 1;
    private static final int _ord_GENDER = _ord_BANK_COUNT + 1;
    private static final int _ord_HOMELAND = _ord_GENDER + 1;
    private static final int _ord_PASSPORT = _ord_HOMELAND + 1;
    private static final int _ord_SSN = _ord_PASSPORT + 1;
    private static final int _ord_DLN = _ord_SSN + 1;

    @Id
    @NotNull
    int id;

    String alias;

    Object contactProperties;

    @NotNull
    String address1;

    @NotNull
    String address2;

    String tel;

    @NotNull
    boolean telok;

    String email;

    @NotNull
    boolean emailok;

    Date birthday;

    @NotNull
    String locale;

    String timezone;

    @NotNull
    int roleCount;

    @NotNull
    boolean customer;

    @NotNull
    boolean supplier;

    @NotNull
    boolean employee;

    String subject;

    @NotNull
    int bankCount;

    String gender;

    String homeland;

    String passport;

    String ssn;

    String dln;

    /**  */
    PartyCategory category;

    Integer categoryId;

    /**  */
    Zone zone;

    Integer zoneId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_ALIAS)
    @Precision(value = N_ALIAS)
    @TextInput(maxLength = N_ALIAS)
    @Column(name = "alias", length = N_ALIAS)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String value) {
        this.alias = value;
    }

    @Ordinal(_ord_CONTACT_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "ctprops", precision = 2147483647)
    public Object getContactProperties() {
        return contactProperties;
    }

    public void setContactProperties(Object value) {
        this.contactProperties = value;
    }

    @Ordinal(_ord_ADDRESS1)
    @NotNull
    @Precision(value = N_ADDRESS1)
    @TextInput(maxLength = N_ADDRESS1)
    @Column(name = "address1", nullable = false, length = N_ADDRESS1)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(@NotNull String value) {
        this.address1 = value;
    }

    @Ordinal(_ord_ADDRESS2)
    @NotNull
    @Precision(value = N_ADDRESS2)
    @TextInput(maxLength = N_ADDRESS2)
    @Column(name = "address2", nullable = false, length = N_ADDRESS2)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(@NotNull String value) {
        this.address2 = value;
    }

    @Ordinal(_ord_TEL)
    @Precision(value = N_TEL)
    @TextInput(maxLength = N_TEL)
    @Column(name = "tel", length = N_TEL)
    public String getTel() {
        return tel;
    }

    public void setTel(String value) {
        this.tel = value;
    }

    @Ordinal(_ord_TELOK)
    @Precision(value = 1)
    @Column(name = "telok", nullable = false, precision = 1)
    public boolean isTelok() {
        return telok;
    }

    public void setTelok(boolean value) {
        this.telok = value;
    }

    @Ordinal(_ord_EMAIL)
    @Precision(value = N_EMAIL)
    @TextInput(maxLength = N_EMAIL)
    @Column(name = "email", length = N_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    @Ordinal(_ord_EMAILOK)
    @Precision(value = 1)
    @Column(name = "emailok", nullable = false, precision = 1)
    public boolean isEmailok() {
        return emailok;
    }

    public void setEmailok(boolean value) {
        this.emailok = value;
    }

    @Ordinal(_ord_BIRTHDAY)
    @Precision(value = 13)
    @Column(name = "birthday", precision = 13)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date value) {
        this.birthday = value;
    }

    @Ordinal(_ord_LOCALE)
    @NotNull
    @Precision(value = N_LOCALE)
    @TextInput(maxLength = N_LOCALE)
    @Column(name = "locale", nullable = false, length = N_LOCALE)
    public String getLocale() {
        return locale;
    }

    public void setLocale(@NotNull String value) {
        this.locale = value;
    }

    @Ordinal(_ord_TIMEZONE)
    @Precision(value = N_TIMEZONE)
    @TextInput(maxLength = N_TIMEZONE)
    @Column(name = "timezone", length = N_TIMEZONE)
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String value) {
        this.timezone = value;
    }

    @Ordinal(_ord_ROLE_COUNT)
    @Precision(value = 10)
    @Column(name = "nrole", nullable = false, precision = 10)
    public int getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(int value) {
        this.roleCount = value;
    }

    @Ordinal(_ord_CUSTOMER)
    @Precision(value = 1)
    @Column(name = "customer", nullable = false, precision = 1)
    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean value) {
        this.customer = value;
    }

    @Ordinal(_ord_SUPPLIER)
    @Precision(value = 1)
    @Column(name = "supplier", nullable = false, precision = 1)
    public boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(boolean value) {
        this.supplier = value;
    }

    @Ordinal(_ord_EMPLOYEE)
    @Precision(value = 1)
    @Column(name = "employee", nullable = false, precision = 1)
    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean value) {
        this.employee = value;
    }

    @Ordinal(_ord_SUBJECT)
    @Precision(value = N_SUBJECT)
    @TextInput(maxLength = N_SUBJECT)
    @Column(name = "subject", length = N_SUBJECT)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    @Ordinal(_ord_BANK_COUNT)
    @Precision(value = 10)
    @Column(name = "nbank", nullable = false, precision = 10)
    public int getBankCount() {
        return bankCount;
    }

    public void setBankCount(int value) {
        this.bankCount = value;
    }

    @Ordinal(_ord_GENDER)
    @Precision(value = N_GENDER)
    @TextInput(maxLength = N_GENDER)
    @Column(name = "gender", length = N_GENDER)
    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    @Ordinal(_ord_HOMELAND)
    @Precision(value = N_HOMELAND)
    @TextInput(maxLength = N_HOMELAND)
    @Column(name = "homeland", length = N_HOMELAND)
    public String getHomeland() {
        return homeland;
    }

    public void setHomeland(String value) {
        this.homeland = value;
    }

    @Ordinal(_ord_PASSPORT)
    @Precision(value = N_PASSPORT)
    @TextInput(maxLength = N_PASSPORT)
    @Column(name = "passport", length = N_PASSPORT)
    public String getPassport() {
        return passport;
    }

    public void setPassport(String value) {
        this.passport = value;
    }

    @Ordinal(_ord_SSN)
    @Precision(value = N_SSN)
    @TextInput(maxLength = N_SSN)
    @Column(name = "ssn", length = N_SSN)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String value) {
        this.ssn = value;
    }

    @Ordinal(_ord_DLN)
    @Precision(value = N_DLN)
    @TextInput(maxLength = N_DLN)
    @Column(name = "dln", length = N_DLN)
    public String getDln() {
        return dln;
    }

    public void setDln(String value) {
        this.dln = value;
    }

    /**
     *
     * @label cat
     * @constraint foreign key (cat) references lily.partycat (id)
     */
    public PartyCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(PartyCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = N_CATEGORY_ID)
    @Column(name = "cat", precision = 10)
    public synchronized Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    /**
     *
     * @label zone
     * @constraint foreign key (zone) references lily.zone (id)
     */
    public Zone getZone() {
        return zone;
    }

    /**
     */
    public void setZone(Zone value) {
        this.zone = value;
    }

    @Ordinal(_ord_ZONE_ID)
    @Precision(value = N_ZONE_ID)
    @Column(name = "zone", precision = 10)
    public synchronized Integer getZoneId() {
        if (zone != null) {
            return zone.getId();
        }
        return zoneId;
    }

    public synchronized void setZoneId(Integer value) {
        this.zoneId = value;
    }

    public void initNotNulls() {
        this.address1 = "";
        this.address2 = "";
        this.locale = "";
    }

}
