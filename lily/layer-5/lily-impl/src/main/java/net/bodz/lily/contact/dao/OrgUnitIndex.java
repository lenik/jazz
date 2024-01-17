package net.bodz.lily.contact.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.model.base.CoIndex;

/**
* @label OrgUnit
*/
@ObjectType(OrgUnit.class)
public class OrgUnitIndex
        extends CoIndex<OrgUnit, OrgUnitCriteriaBuilder> {

    public OrgUnitIndex() {
    }

}
