package net.bodz.lily.schema.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoCategory;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ArticleCategory_stuff<this_t extends _ArticleCategory_stuff<this_t>>
        extends CoCategory<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "articlecat";

    public static final String FIELD_NAME = "name";

    public static final int N_NAME = 30;

    private static final int _ord_NAME = 2;

    String name;

    @Ordinal(_ord_NAME)
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", length = N_NAME)
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public void initNotNulls() {
    }

}
