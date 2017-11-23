package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanDoTagMask
 * @see net.bodz.violet.plan.impl.PlanDoTagMapper
 * @see net.bodz.violet.plan.impl.PlanDoTag_htm
 * @see net.bodz.violet.plan.impl.PlanDoTagIndex
 * @see net.bodz.violet.plan.impl.PlanDoTagIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoTagMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoTag_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoTag_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoTagIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanDoTagIndex_htm.js
*/
@IdType(Integer.class)
public class PlanDoTag
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanDoTag() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogTag: ...");
        return sb.toString();
    }

}
