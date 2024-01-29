package net.bodz.lily.schema.contact;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoCategory;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _PartyCategory_stuff<this_t extends _PartyCategory_stuff<this_t>>
        extends CoCategory<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "partycat";

    public static final String FIELD_UNIQ_NAME = "name";

    public static final int N_UNIQ_NAME = 30;

    private static final int _ord_UNIQ_NAME = 2;

    String uniqName;

    @Ordinal(_ord_UNIQ_NAME)
    @Precision(value = N_UNIQ_NAME)
    @TextInput(maxLength = N_UNIQ_NAME)
    @Column(name = "name", length = N_UNIQ_NAME)
    public String getUniqName() {
        return uniqName;
    }

    public void setUniqName(String value) {
        this.uniqName = value;
    }

    public void initNotNulls() {
    }

}
