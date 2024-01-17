package net.bodz.violet.shop.dao;

import net.bodz.lily.template.CoCategoryCriteriaBuilder;

public class _ShopItemCategoryCriteriaBuilder_stuff<self_t extends _ShopItemCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField image = string("image");

    public final StringField imageAlt = string("imagealt");

    public final IntegerField refCount = integer("nref");

}
