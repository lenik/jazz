package net.bodz.violet.schema.issue;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoParameter;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _IssueParameter_stuff<this_t extends _IssueParameter_stuff<this_t>>
        extends CoParameter<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "issueparm";

    public static final String FIELD_DUMMY = "dummy";

    public static final int N_DUMMY = 10;

    private static final int _ord_DUMMY = 15;

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
