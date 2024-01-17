package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.FormDef;

/**
* @label FormDef
*/
@ObjectType(FormDef.class)
public class FormDefIndex
        extends CoIndex<FormDef, FormDefCriteriaBuilder> {

    public FormDefIndex() {
    }

}
