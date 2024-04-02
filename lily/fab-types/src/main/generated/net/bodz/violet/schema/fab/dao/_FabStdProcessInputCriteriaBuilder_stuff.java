package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabStdProcessInput;

@ForEntityType(FabStdProcessInput.class)
public class _FabStdProcessInputCriteriaBuilder_stuff<self_t extends _FabStdProcessInputCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField processId = integer("proc");

    public final IntegerField modelId = integer("model");

    public final IntegerField artifactId = integer("art");

    public final BigDecimalField quantity = bigDecimal("qty");

}
