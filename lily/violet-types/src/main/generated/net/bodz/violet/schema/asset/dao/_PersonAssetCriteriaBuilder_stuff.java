package net.bodz.violet.schema.asset.dao;

import java.time.OffsetDateTime;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.asset.PersonAsset;

@ForEntityType(PersonAsset.class)
public class _PersonAssetCriteriaBuilder_stuff<self_t extends _PersonAssetCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final IntegerField regionId = integer("region");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final BigDecimalField quantity = bigDecimal("qty");

    public final LongField serial = _long("serial");

    public final DateField<OffsetDateTime> expire = date("expire", OffsetDateTime.class);

}
