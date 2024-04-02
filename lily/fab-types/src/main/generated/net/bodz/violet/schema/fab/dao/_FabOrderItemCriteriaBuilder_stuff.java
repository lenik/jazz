package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.concrete.CoImagedEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.fab.FabOrderItem;

@ForEntityType(FabOrderItem.class)
public class _FabOrderItemCriteriaBuilder_stuff<self_t extends _FabOrderItemCriteriaBuilder_stuff<self_t>>
        extends CoImagedEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField orderId = _long("odr");

    public final IntegerField artifactId = integer("art");

    public final BooleanField resale = bool("resale");

    public final StringField altLabel = string("o_label");

    public final StringField altSpec = string("o_spec");

    public final StringField altUom = string("o_uom");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField amount = bigDecimal("amount");

    public final StringField notes = string("notes");

}
