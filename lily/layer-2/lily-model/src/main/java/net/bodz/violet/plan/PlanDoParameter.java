package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanDoParameterMask
 * @see net.bodz.violet.plan.impl.PlanDoParameterMapper
 * @see net.bodz.violet.plan.impl.PlanDoParameter_htm
 * @see net.bodz.violet.plan.impl.PlanDoParameterIndex
 * @see net.bodz.violet.plan.impl.PlanDoParameterIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoParameterMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoParameter_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoParameter_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoParameterIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoParameterIndex_htm.js
*/
@IdType(Integer.class)
public class PlanDoParameter
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanDoParameter() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogParameter: ...");
        return sb.toString();
    }

}
