package net.bodz.lily.concrete;

import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

//@FieldGroupVue
//@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public abstract class CoRelation<Id>
        extends IdEntity<Id> {

    private static final long serialVersionUID = 1L;

}
