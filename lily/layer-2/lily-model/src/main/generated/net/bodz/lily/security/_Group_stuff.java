package net.bodz.lily.security;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * User Group
 */
@IdType(Integer.class)
public abstract class _Group_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_NAME = 32;
    public static final int N_PARENT_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_NAME = _ord_ID + 1;
    private static final int _ord_TYPE_ID = _ord_NAME + 1;
    private static final int _ord_PARENT_ID = _ord_TYPE_ID + 10;

    @Id
    @NotNull
    int id;

    /** The group name (unique) */
    @NotNull
    String name;

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

    /**
     * The group name (unique)
     */
    @Ordinal(_ord_NAME)
    @NotNull
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", nullable = false, length = N_NAME)
    public String getName() {
        return name;
    }

    /**
     * The group name (unique)
     */
    public void setName(@NotNull String value) {
        this.name = value;
    }

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
        this.name = "";
    }

}
