package net.bodz.lily.schema.contact;

import javax.persistence.Column;

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

    public static final String FIELD_ORG_ID = "org";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_DEPTH = "depth";

    public static final int N_ORG_ID = 10;
    public static final int N_PARENT_ID = 10;
    public static final int N_DEPTH = 10;

    private static final int _ord_ORG_ID = 24;
    private static final int _ord_PARENT_ID = _ord_ORG_ID + 1;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;

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

    public void initNotNulls() {
        this.getContact().setAddress1("");
        this.getContact().setAddress2("");
    }

}
