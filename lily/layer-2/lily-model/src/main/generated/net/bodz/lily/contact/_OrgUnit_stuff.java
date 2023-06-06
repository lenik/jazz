package net.bodz.lily.contact;

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
public abstract class _OrgUnit_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_ALIAS = 32;
    public static final int N_ADDRESS1 = 80;
    public static final int N_ADDRESS2 = 80;
    public static final int N_ZONE_ID = 10;
    public static final int N_TEL = 20;
    public static final int N_EMAIL = 30;
    public static final int N_PARENT_ID = 10;

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
    private static final int _ord_ORG_ID = _ord_EMAILOK + 2;
    private static final int _ord_PARENT_ID = _ord_ORG_ID + 1;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;

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

    @NotNull
    int depth;

    /**  */
    @NotNull
    Organization org;

    @NotNull
    int orgId;

    /**  */
    OrgUnit parent;

    Integer parentId;

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

    @Ordinal(_ord_DEPTH)
    @Precision(value = 10)
    @Column(name = "depth", nullable = false, precision = 10)
    public int getDepth() {
        return depth;
    }

    public void setDepth(int value) {
        this.depth = value;
    }

    /**
     *
     * @label org
     * @constraint foreign key (org) references lily.org (id)
     */
    @NotNull
    public Organization getOrg() {
        return org;
    }

    /**
     */
    public void setOrg(@NotNull Organization value) {
        this.org = value;
    }

    @Ordinal(_ord_ORG_ID)
    @Precision(value = 10)
    @Column(name = "org", nullable = false, precision = 10)
    public synchronized int getOrgId() {
        if (org != null) {
            if (org.getId() == null)
                return 0;
            return org.getId();
        }
        return orgId;
    }

    public synchronized void setOrgId(int value) {
        this.orgId = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references lily.orgunit (id)
     */
    public OrgUnit getParent() {
        return parent;
    }

    /**
     */
    public void setParent(OrgUnit value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 10)
    public synchronized Integer getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Integer value) {
        this.parentId = value;
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
