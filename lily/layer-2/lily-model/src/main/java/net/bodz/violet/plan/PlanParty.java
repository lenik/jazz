package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanPartyMask
 * @see net.bodz.violet.plan.impl.PlanPartyMapper
 * @see net.bodz.violet.plan.impl.PlanParty_htm
 * @see net.bodz.violet.plan.impl.PlanPartyIndex
 * @see net.bodz.violet.plan.impl.PlanPartyIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanPartyMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParty_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParty_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanPartyIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanPartyIndex_htm.js
*/
@IdType(Integer.class)
public class PlanParty
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanParty() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planParty: ...");
        return sb.toString();
    }

}
