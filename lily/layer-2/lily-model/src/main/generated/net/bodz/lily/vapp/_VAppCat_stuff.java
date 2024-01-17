package net.bodz.lily.vapp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _VAppCat_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vappcat";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_DEPTH = "depth";
    public static final String FIELD_REF_COUNT = "nref";

    public static final int N_ID = 10;
    public static final int N_PARENT_ID = 10;
    public static final int N_DEPTH = 10;
    public static final int N_REF_COUNT = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_PARENT_ID = 18;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;
    private static final int _ord_REF_COUNT = _ord_DEPTH + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    int depth;

    @NotNull
    int refCount;

    /**  */
    VAppCat parent;

    Integer parentId;

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

    @Ordinal(_ord_DEPTH)
    @Precision(value = 10)
    @Column(name = "depth", nullable = false, precision = 10)
    public int getDepth() {
        return depth;
    }

    public void setDepth(int value) {
        this.depth = value;
    }

    @Ordinal(_ord_REF_COUNT)
    @Precision(value = 10)
    @Column(name = "nref", nullable = false, precision = 10)
    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int value) {
        this.refCount = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references lily.vappcat (id)
     */
    public VAppCat getParent() {
        return parent;
    }

    /**
     */
    public void setParent(VAppCat value) {
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
