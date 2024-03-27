package net.bodz.violet.schema.store.dao;

import java.time.OffsetDateTime;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedEventCriteriaBuilder;

public class _StoreOrderItemCriteriaBuilder_stuff<self_t extends _StoreOrderItemCriteriaBuilder_stuff<self_t>>
        extends CoImagedEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField orderId = _long("odr");

    public final IntegerField artifactId = integer("art");

    public final IntegerField regionId = integer("region");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final LongField serial = _long("serial");

    public final DateField<OffsetDateTime> expire = date("expire", OffsetDateTime.class);

    public final BigDecimalField quantity = bigDecimal("qty");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField amount = bigDecimal("amount");

    public final StringField notes = string("notes");

}
