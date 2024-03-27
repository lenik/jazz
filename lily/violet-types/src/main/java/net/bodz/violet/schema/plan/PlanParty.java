package net.bodz.violet.schema.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns("id")
@PrimaryKeyProperties("id")
@Table(schema = PlanParty.SCHEMA_NAME, name = PlanParty.TABLE_NAME)
public class PlanParty
        extends _PlanParty_stuff {

    private static final long serialVersionUID = 1L;

}
