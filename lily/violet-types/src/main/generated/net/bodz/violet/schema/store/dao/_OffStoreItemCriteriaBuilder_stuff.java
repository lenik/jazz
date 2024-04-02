package net.bodz.violet.schema.store.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.store.OffStoreItem;

@ForEntityType(OffStoreItem.class)
public class _OffStoreItemCriteriaBuilder_stuff<self_t extends _OffStoreItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final BigDecimalField quantity = bigDecimal("qty");

}
