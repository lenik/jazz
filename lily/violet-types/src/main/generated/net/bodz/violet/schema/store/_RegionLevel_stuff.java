package net.bodz.violet.schema.store;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoCode;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _RegionLevel_stuff<this_t extends _RegionLevel_stuff<this_t>>
        extends CoCode<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "regionlevel";

    public static final String FIELD_DUMMY = "dummy";

    public static final int N_DUMMY = 10;

    private static final int _ord_DUMMY = 12;

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
