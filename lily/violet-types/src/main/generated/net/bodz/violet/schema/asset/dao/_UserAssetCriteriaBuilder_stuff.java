package net.bodz.violet.schema.asset.dao;

import java.sql.Timestamp;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoEventCriteriaBuilder;

public class _UserAssetCriteriaBuilder_stuff<self_t extends _UserAssetCriteriaBuilder_stuff<self_t>>
        extends CoEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final IntegerField regionId = integer("region");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final BigDecimalField quantity = bigDecimal("qty");

    public final LongField serial = _long("serial");

    public final DateField<Timestamp> expire = date("expire", Timestamp.class);

}
