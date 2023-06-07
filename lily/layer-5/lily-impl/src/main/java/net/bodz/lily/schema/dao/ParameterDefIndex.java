package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.ParameterDef;

/**
* @label ParameterDef
*/
@ObjectType(ParameterDef.class)
public class ParameterDefIndex
        extends CoIndex<ParameterDef, ParameterDefMask> {

    public ParameterDefIndex() {
    }

}