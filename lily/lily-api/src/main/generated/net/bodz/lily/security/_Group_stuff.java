package net.bodz.lily.security;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

/**
 * User Group
 */
@IdType(Integer.class)
public abstract class _Group_stuff
        extends CoPrincipal {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "group";

    public static final String FIELD_TYPE_ID = "type";
    public static final String FIELD_PARENT_ID = "parent";

    public static final int N_TYPE_ID = 10;
    public static final int N_PARENT_ID = 10;

    private static final int _ord_TYPE_ID = 3;
    private static final int _ord_PARENT_ID = _ord_TYPE_ID + 10;

    /** The parent group. must be acyclic */
    Group parent;

    /** The parent group. must be acyclic */
    Integer parentId;

    /** Group type like normal-group, role-group, etc. */
    @NotNull
    GroupType type;

    /** Group type like normal-group, role-group, etc. */
    @NotNull
    int typeId;

    /**
     * The parent group. must be acyclic
     *
     * @label parent
     * @constraint foreign key (parent) references lily.group (id)
     */
    public Group getParent() {
        return parent;
    }

    /**
     * The parent group. must be acyclic
     */
    public void setParent(Group value) {
        this.parent = value;
    }

    /**
     * The parent group. must be acyclic
     */
    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 10)
    public synchronized Integer getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    /**
     * The parent group. must be acyclic
     */
    public synchronized void setParentId(Integer value) {
        this.parentId = value;
    }

    /**
     * Group type like normal-group, role-group, etc.
     *
     * @label type
     * @constraint foreign key (type) references lily.grouptype (id)
     */
    @NotNull
    public GroupType getType() {
        return type;
    }

    /**
     * Group type like normal-group, role-group, etc.
     */
    public void setType(@NotNull GroupType value) {
        this.type = value;
    }

    /**
     * Group type like normal-group, role-group, etc.
     */
    @Ordinal(_ord_TYPE_ID)
    @Precision(value = 10)
    @Column(name = "type", nullable = false, precision = 10)
    public synchronized int getTypeId() {
        if (type != null) {
            return type.getId();
        }
        return typeId;
    }

    /**
     * Group type like normal-group, role-group, etc.
     */
    public synchronized void setTypeId(int value) {
        this.typeId = value;
    }

    public void initNotNulls() {
    }

}
