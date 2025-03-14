package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.shop.SalesOrderItem;

@ForEntityType(SalesOrderItem.class)
public class _SalesOrderItemCriteriaBuilder_stuff<self_t extends _SalesOrderItemCriteriaBuilder_stuff<self_t>>
        extends CoImagedEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final LongField orderId = _long("odr");

    public final LongField shopItemId = _long("shopitem");

    public final IntegerField artifactId = integer("art");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final BigDecimalField quantity = bigDecimal("qty");

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField amount = bigDecimal("amount");

    public final BigDecimalField n1 = bigDecimal("n1");

}
