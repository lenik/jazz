package net.bodz.lily.schema.account;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

/**
 * Group Type
 */
@IdType(Integer.class)
public abstract class _GroupType_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "grouptype";

    public static final String FIELD_NAME = "name";
    public static final String FIELD_DUMMY = "dummy";

    public static final int N_NAME = 20;
    public static final int N_DUMMY = 10;

    private static final int _ord_NAME = 2;
    private static final int _ord_DUMMY = 14;

    /** Group type name (unique) */
    @NotNull
    String name;

    Integer dummy;

    /**
     * Group type name (unique)
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
     * Group type name (unique)
     */
    public void setName(@NotNull String value) {
        this.name = value;
    }

    @Ordinal(_ord_DUMMY)
    @Precision(value = N_DUMMY)
    @Column(name = "dummy", precision = 10)
    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer value) {
        this.dummy = value;
    }

    public void initNotNulls() {
        this.name = "";
    }

}
