package net.bodz.violet.schema.plan;

import javax.persistence.Table;

/**
 * 机会、计划分类
 */
@Table(schema = "violet", name = "plancat")
public class PlanCategory
        extends _PlanCategory_stuff<PlanCategory> {

    private static final long serialVersionUID = 1L;

}
