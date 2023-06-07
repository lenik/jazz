package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.FormParameter;

/**
* @label FormParameter
*/
@ObjectType(FormParameter.class)
public class FormParameterIndex
        extends CoIndex<FormParameter, FormParameterMask> {

    public FormParameterIndex() {
    }

}