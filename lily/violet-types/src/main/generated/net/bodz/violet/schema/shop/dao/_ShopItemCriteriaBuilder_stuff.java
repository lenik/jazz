package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedEventCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.shop.ShopItem;

@ForEntityType(ShopItem.class)
public class _ShopItemCriteriaBuilder_stuff<self_t extends _ShopItemCriteriaBuilder_stuff<self_t>>
        extends CoImagedEventCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField shopId = integer("shop");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField artifactId = integer("art");

    public final DiscreteField<JsonVariant> batch = discrete("batch", JsonVariant.class);

    public final BigDecimalField price = bigDecimal("price");

    public final BigDecimalField quantity = bigDecimal("qty");

}
