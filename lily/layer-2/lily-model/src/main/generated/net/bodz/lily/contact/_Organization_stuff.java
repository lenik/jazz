package net.bodz.lily.contact;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.geo.Zone;

@IdType(Integer.class)
public abstract class _Organization_stuff
        extends Party {

    private static final long serialVersionUID = 1L;

    public static final int N_ALIAS = 32;
    public static final int N_ADDRESS1 = 80;
    public static final int N_ADDRESS2 = 80;
    public static final int N_ZONE_ID = 10;
    public static final int N_TEL = 20;
    public static final int N_EMAIL = 30;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_TIMEZONE = 40;
    public static final int N_TAXID = 20;

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
    private static final int _ord_TIMEZONE = _ord_CATEGORY_ID + 3;
    private static final int _ord_ROLE_COUNT = _ord_TIMEZONE + 1;
    private static final int _ord_BANK_COUNT = _ord_ROLE_COUNT + 4;
    private static final int _ord_SIZE = _ord_BANK_COUNT + 1;
    private static final int _ord_TAXID = _ord_SIZE + 1;

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

    String timezone;

    @NotNull
    int roleCount;

    @NotNull
    int bankCount;

    @NotNull
    int size;

    String taxid;

    /**  */
    PartyCategory category;

    Integer categoryId;

    /**  */
    Zone zone;

    Integer zoneId;

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

    @Ordinal(_ord_BANK_COUNT)
    @Precision(value = 10)
    @Column(name = "nbank", nullable = false, precision = 10)
    public int getBankCount() {
        return bankCount;
    }

    public void setBankCount(int value) {
        this.bankCount = value;
    }

    @Ordinal(_ord_SIZE)
    @Precision(value = 10)
    @Column(name = "size", nullable = false, precision = 10)
    public int getSize() {
        return size;
    }

    public void setSize(int value) {
        this.size = value;
    }

    @Ordinal(_ord_TAXID)
    @Precision(value = N_TAXID)
    @TextInput(maxLength = N_TAXID)
    @Column(name = "taxid", length = N_TAXID)
    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String value) {
        this.taxid = value;
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
    }

}
