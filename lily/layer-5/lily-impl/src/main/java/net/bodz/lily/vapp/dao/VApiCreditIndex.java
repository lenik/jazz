package net.bodz.lily.vapp.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.vapp.VApiCredit;

/**
* @label VApiCredit
*/
@ObjectType(VApiCredit.class)
public class VApiCreditIndex
        extends CoIndex<VApiCredit, VApiCreditCriteriaBuilder> {

    public VApiCreditIndex() {
    }

}
