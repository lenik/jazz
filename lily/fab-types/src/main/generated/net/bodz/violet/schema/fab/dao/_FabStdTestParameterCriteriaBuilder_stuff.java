package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabStdTestParameter;

@ForEntityType(FabStdTestParameter.class)
public class _FabStdTestParameterCriteriaBuilder_stuff<self_t extends _FabStdTestParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField testId = integer("test");

    public final BooleanField required = bool("required");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final StringField expected = string("expected");

}
