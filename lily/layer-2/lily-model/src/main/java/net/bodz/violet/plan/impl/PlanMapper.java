package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.impl.PlanMask;

/**
 * @mapper.xml PlanMapper.xml
 */
public interface PlanMapper
        extends IMapperTemplate<Plan, PlanMask> {

}
