package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.DiaryParty;

/**
* @label DiaryParty
*/
@ObjectType(DiaryParty.class)
public class DiaryPartyIndex
        extends CoIndex<DiaryParty, DiaryPartyCriteriaBuilder> {

    public DiaryPartyIndex() {
    }

}
