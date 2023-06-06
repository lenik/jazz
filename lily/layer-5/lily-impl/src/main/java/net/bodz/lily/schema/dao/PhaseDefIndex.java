package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.PhaseDef;

/**
* @label PhaseDef
*/
@ObjectType(PhaseDef.class)
public class PhaseDefIndex
        extends CoIndex<PhaseDef, PhaseDefMask> {

    public PhaseDefIndex() {
    }

}
