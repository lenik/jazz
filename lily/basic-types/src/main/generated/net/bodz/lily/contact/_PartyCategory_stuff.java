package net.bodz.lily.contact;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoCategory;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _PartyCategory_stuff<this_t extends _PartyCategory_stuff<this_t>>
        extends CoCategory<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "partycat";

    public void initNotNulls() {
    }

}
