package net.bodz.violet.schema.fab;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _FabStdTest_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabstdtest";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_DEPTH = "depth";
    public static final String FIELD_REF_COUNT = "nref";

    public static final int N_CODE = 20;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PARENT_ID = 10;
    public static final int N_DEPTH = 10;
    public static final int N_REF_COUNT = 10;

    private static final int _ord_CODE = 2;
    private static final int _ord_CATEGORY_ID = 18;
    private static final int _ord_PARENT_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;
    private static final int _ord_REF_COUNT = _ord_DEPTH + 1;

    String code;

    @NotNull
    int depth;

    @NotNull
    int refCount;

    /**  */
    @NotNull
    FabStdTestCategory category;

    @NotNull
    int categoryId;

    /**  */
    FabStdTest parent;

    Integer parentId;

    @Ordinal(_ord_CODE)
    @Precision(value = N_CODE)
    @TextInput(maxLength = N_CODE)
    @Column(name = "code", length = N_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
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
     * @constraint foreign key (cat) references violet.fabstdtestcat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    @NotNull
    public FabStdTestCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull FabStdTestCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            if (category.getId() == null)
                return 0;
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     *
     * @constraint foreign key (parent) references violet.fabstdtest (id)
     */
    @JoinColumn(name = "parent")
    @ManyToOne
    public FabStdTest getParent() {
        return parent;
    }

    /**
     */
    public void setParent(FabStdTest value) {
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
