package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanLogTagMask
 * @see net.bodz.violet.plan.impl.PlanLogTagMapper
 * @see net.bodz.violet.plan.impl.PlanLogTag_htm
 * @see net.bodz.violet.plan.impl.PlanLogTagIndex
 * @see net.bodz.violet.plan.impl.PlanLogTagIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogTagMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogTag_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogTag_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogTagIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogTagIndex_htm.js
*/
@IdType(Integer.class)
public class PlanLogTag
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanLogTag() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogTag: ...");
        return sb.toString();
    }

}
