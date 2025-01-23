package net.bodz.violet.schema.store.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.store.RegionLevel;

@ForEntityType(RegionLevel.class)
public class _RegionLevelCriteriaBuilder_stuff<self_t extends _RegionLevelCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField dummy = integer("dummy");

}
