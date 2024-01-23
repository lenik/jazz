package net.bodz.violet.shop.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _SellPriceCriteriaBuilder_stuff<self_t extends _SellPriceCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final BigDecimalField price = bigDecimal("price");

}
