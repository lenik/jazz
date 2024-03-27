package net.bodz.lily.schema.account;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;

/**
 * Type of Open ID
 */
@IdType(Integer.class)
public abstract class _UserOtherIdType_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "useroidtype";

    public static final String FIELD_DUMMY = "dummy";

    public static final int N_DUMMY = 10;

    private static final int _ord_DUMMY = 11;

    Integer dummy;

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
    }

}
