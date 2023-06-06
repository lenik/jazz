package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.ParameterValue;

/**
* @label ParameterValue
*/
@ObjectType(ParameterValue.class)
public class ParameterValueIndex
        extends CoIndex<ParameterValue, ParameterValueMask> {

    public ParameterValueIndex() {
    }

}
