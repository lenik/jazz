package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.meta.PhaseDef;

@ForEntityType(PhaseDef.class)
public class _PhaseDefCriteriaBuilder_stuff<self_t extends _PhaseDefCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField schemaId = integer("\"schema\"");

}
