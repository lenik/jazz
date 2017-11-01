package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanParameterMask
 * @see net.bodz.violet.plan.impl.PlanParameterMapper
 * @see net.bodz.violet.plan.impl.PlanParameter_htm
 * @see net.bodz.violet.plan.impl.PlanParameterIndex
 * @see net.bodz.violet.plan.impl.PlanParameterIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParameterMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParameter_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParameter_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParameterIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanParameterIndex_htm.js
*/
@IdType(Integer.class)
public class PlanParameter
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanParameter() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planParameter: ...");
        return sb.toString();
    }

}
