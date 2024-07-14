package net.bodz.lily.schema.inet;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoNode;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ExternalSite_stuff<this_t extends _ExternalSite_stuff<this_t>>
        extends CoNode<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "extsite";

    public static final String FIELD_URLFMT = "urlfmt";
    public static final String FIELD_BONUS = "bonus";
    public static final String FIELD_COUNT = "count";

    public static final int N_URLFMT = 200;
    public static final int N_BONUS = 10;
    public static final int N_COUNT = 10;

    private static final int _ord_URLFMT = 18;
    private static final int _ord_BONUS = _ord_URLFMT + 1;
    private static final int _ord_COUNT = _ord_BONUS + 1;

    String urlfmt;

    @NotNull
    int bonus;

    @NotNull
    int count;

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

    public void initNotNulls() {
    }

}
