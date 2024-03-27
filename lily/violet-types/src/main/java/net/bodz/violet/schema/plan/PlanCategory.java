package net.bodz.violet.schema.plan;

import javax.persistence.Table;

@Table(schema = PlanCategory.SCHEMA_NAME, name = PlanCategory.TABLE_NAME)
public class PlanCategory
        extends _PlanCategory_stuff<PlanCategory> {

    private static final long serialVersionUID = 1L;

}
