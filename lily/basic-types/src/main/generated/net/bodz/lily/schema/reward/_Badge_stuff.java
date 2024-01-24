package net.bodz.lily.schema.reward;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Badge_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "badge";

    public static final String FIELD_ID = "id";
    public static final String FIELD_EXPR = "expr";
    public static final String FIELD_VAL = "val";
    public static final String FIELD_LEVELS = "levels";
    public static final String FIELD_DESCEND = "descend";
    public static final String FIELD_TRANSIENT = "transient";
    public static final String FIELD_INDEXED = "indexed";

    public static final int N_ID = 10;
    public static final int N_EXPR = 255;
    public static final int N_VAL = 10;
    public static final int N_LEVELS = 10;
    public static final int N_DESCEND = 1;
    public static final int N_TRANSIENT = 1;
    public static final int N_INDEXED = 1;

    private static final int _ord_ID = 1;
    private static final int _ord_EXPR = _ord_ID + 9;
    private static final int _ord_VAL = _ord_EXPR + 1;
    private static final int _ord_LEVELS = _ord_VAL + 1;
    private static final int _ord_DESCEND = _ord_LEVELS + 1;
    private static final int _ord_TRANSIENT = _ord_DESCEND + 1;
    private static final int _ord_INDEXED = _ord_TRANSIENT + 1;

    @Id
    @NotNull
    int id;

    String expr;

    @NotNull
    int val;

    int[] levels;

    @NotNull
    boolean descend;

    @NotNull
    boolean transient_;

    @NotNull
    boolean indexed;

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

    @Ordinal(_ord_EXPR)
    @Precision(value = N_EXPR)
    @TextInput(maxLength = N_EXPR)
    @Column(name = "expr", length = N_EXPR)
    public String getExpr() {
        return expr;
    }

    public void setExpr(String value) {
        this.expr = value;
    }

    @Ordinal(_ord_VAL)
    @Precision(value = 10)
    @Column(name = "val", nullable = false, precision = 10)
    public int getVal() {
        return val;
    }

    public void setVal(int value) {
        this.val = value;
    }

    @Ordinal(_ord_LEVELS)
    @Precision(value = 10)
    @Column(name = "levels", precision = 10)
    public int[] getLevels() {
        return levels;
    }

    public void setLevels(int[] value) {
        this.levels = value;
    }

    @Ordinal(_ord_DESCEND)
    @Precision(value = 1)
    @Column(name = "descend", nullable = false, precision = 1)
    public boolean isDescend() {
        return descend;
    }

    public void setDescend(boolean value) {
        this.descend = value;
    }

    @Ordinal(_ord_TRANSIENT)
    @Precision(value = 1)
    @Column(name = "transient", nullable = false, precision = 1)
    public boolean isTransient_() {
        return transient_;
    }

    public void setTransient_(boolean value) {
        this.transient_ = value;
    }

    @Ordinal(_ord_INDEXED)
    @Precision(value = 1)
    @Column(name = "indexed", nullable = false, precision = 1)
    public boolean isIndexed() {
        return indexed;
    }

    public void setIndexed(boolean value) {
        this.indexed = value;
    }

    public void initNotNulls() {
    }

}
