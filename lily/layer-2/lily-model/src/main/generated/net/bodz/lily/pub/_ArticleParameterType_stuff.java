package net.bodz.lily.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoParameter;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ArticleParameterType_stuff<this_t extends _ArticleParameterType_stuff<this_t>>
        extends CoParameter<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "articleparm";

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
