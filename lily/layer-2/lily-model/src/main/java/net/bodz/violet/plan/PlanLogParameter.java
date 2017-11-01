package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanLogParameterMask
 * @see net.bodz.violet.plan.impl.PlanLogParameterMapper
 * @see net.bodz.violet.plan.impl.PlanLogParameter_htm
 * @see net.bodz.violet.plan.impl.PlanLogParameterIndex
 * @see net.bodz.violet.plan.impl.PlanLogParameterIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogParameterMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogParameter_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogParameter_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogParameterIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogParameterIndex_htm.js
*/
@IdType(Integer.class)
public class PlanLogParameter
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanLogParameter() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogParameter: ...");
        return sb.toString();
    }

}
