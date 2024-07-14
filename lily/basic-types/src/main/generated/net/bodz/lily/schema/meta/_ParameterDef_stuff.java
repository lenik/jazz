package net.bodz.lily.schema.meta;

import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ParameterDef_stuff<this_t extends _ParameterDef_stuff<this_t>>
        extends AbstractDefinition<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_parm";

    public void initNotNulls() {
    }

}
