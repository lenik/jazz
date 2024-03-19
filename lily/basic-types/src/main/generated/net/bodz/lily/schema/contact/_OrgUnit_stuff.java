package net.bodz.lily.schema.contact;

import javax.persistence.Column;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _OrgUnit_stuff
        extends Party {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "orgunit";

    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_ORG_ID = "org";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_DEPTH = "depth";

    public static final int N_PROPERTIES = 2147483647;
    public static final int N_ORG_ID = 10;
    public static final int N_PARENT_ID = 10;
    public static final int N_DEPTH = 10;

    private static final int _ord_PROPERTIES = 24;
    private static final int _ord_ORG_ID = _ord_PROPERTIES + 1;
    private static final int _ord_PARENT_ID = _ord_ORG_ID + 1;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;

    JsonVariant properties;

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

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
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

    public void initNotNulls() {
    }

}
