package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.shop.ShopItemCategory;

@ForEntityType(ShopItemCategory.class)
public class _ShopItemCategoryCriteriaBuilder_stuff<self_t extends _ShopItemCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField name = string("\"name\"");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
