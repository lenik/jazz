package net.bodz.violet.schema.issue;

import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.lily.concrete.CoParameter;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _IssueParameter_stuff<this_t extends _IssueParameter_stuff<this_t>>
        extends CoParameter<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "issueparm";

    public void initNotNulls() {
    }

}
