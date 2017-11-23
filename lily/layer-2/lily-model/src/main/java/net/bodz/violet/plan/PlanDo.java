package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanDoMask
 * @see net.bodz.violet.plan.impl.PlanDoMapper
 * @see net.bodz.violet.plan.impl.PlanDo_htm
 * @see net.bodz.violet.plan.impl.PlanDoIndex
 * @see net.bodz.violet.plan.impl.PlanDoIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDo_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDo_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoIndex_htm.js
*/
@IdType(Integer.class)
public class PlanDo
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanDo() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLog: ...");
        return sb.toString();
    }

}
