package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * @see net.bodz.violet.plan.impl.PlanLogMask
 * @see net.bodz.violet.plan.impl.PlanLogMapper
 * @see net.bodz.violet.plan.impl.PlanLog_htm
 * @see net.bodz.violet.plan.impl.PlanLogIndex
 * @see net.bodz.violet.plan.impl.PlanLogIndex_htm
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogMapper.xml
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLog_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLog_htm.js
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogIndex_htm.css
 * @see src/main/resources/net/bodz/violet/plan/impl/PlanLogIndex_htm.js
*/
@IdType(Integer.class)
public class PlanLog
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;
    
    public PlanLog() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLog: ...");
        return sb.toString();
    }

}
