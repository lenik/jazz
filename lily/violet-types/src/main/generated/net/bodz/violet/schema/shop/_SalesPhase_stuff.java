package net.bodz.violet.schema.shop;

import net.bodz.lily.concrete.CoPhase;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _SalesPhase_stuff
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "salephase";

    public void initNotNulls() {
    }

}
