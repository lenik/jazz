package net.bodz.lily.schema.inet;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ExternalSite_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "extsite";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_DEPTH = "depth";
    public static final String FIELD_URLFMT = "urlfmt";
    public static final String FIELD_BONUS = "bonus";
    public static final String FIELD_COUNT = "count";

    public static final int N_ID = 10;
    public static final int N_PARENT_ID = 10;
    public static final int N_DEPTH = 10;
    public static final int N_URLFMT = 200;
    public static final int N_BONUS = 10;
    public static final int N_COUNT = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_PARENT_ID = 16;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;
    private static final int _ord_URLFMT = _ord_DEPTH + 1;
    private static final int _ord_BONUS = _ord_URLFMT + 1;
    private static final int _ord_COUNT = _ord_BONUS + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    int depth;

    String urlfmt;

    @NotNull
    int bonus;

    @NotNull
    int count;

    /**  */
    ExternalSite parent;

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

    @Ordinal(_ord_URLFMT)
    @Precision(value = N_URLFMT)
    @TextInput(maxLength = N_URLFMT)
    @Column(name = "urlfmt", length = N_URLFMT)
    public String getUrlfmt() {
        return urlfmt;
    }

    public void setUrlfmt(String value) {
        this.urlfmt = value;
    }

    @Ordinal(_ord_BONUS)
    @Precision(value = 10)
    @Column(name = "bonus", nullable = false, precision = 10)
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int value) {
        this.bonus = value;
    }

    @Ordinal(_ord_COUNT)
    @Precision(value = 10)
    @Column(name = "count", nullable = false, precision = 10)
    public int getCount() {
        return count;
    }

    public void setCount(int value) {
        this.count = value;
    }

    /**
     *
     * @constraint foreign key (parent) references lily.extsite (id)
     */
    public ExternalSite getParent() {
        return parent;
    }

    /**
     */
    public void setParent(ExternalSite value) {
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
