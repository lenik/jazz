package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.shop.Shop;

@ForEntityType(Shop.class)
public class _ShopCriteriaBuilder_stuff<self_t extends _ShopCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField code = string("code");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField supplierOrgId = integer("supplierorg");

    public final IntegerField supplierId = integer("supplier");

    public final IntegerField hydm = integer("hydm");

}
