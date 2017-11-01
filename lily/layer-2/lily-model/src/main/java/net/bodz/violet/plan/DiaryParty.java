package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.DiaryPartyMask
 * @see net.bodz.violet.plan.impl.DiaryPartyMapper
 * @see net.bodz.violet.plan.impl.DiaryParty_htm
 * @see net.bodz.violet.plan.impl.DiaryPartyIndex
 * @see net.bodz.violet.plan.impl.DiaryPartyIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPartyMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParty_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryParty_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPartyIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/DiaryPartyIndex_htm.js
*/
@IdType(Integer.class)
public class DiaryParty
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public DiaryParty() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryParty: ...");
        return sb.toString();
    }

}
