package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _VApiCreditCriteriaBuilder_stuff<self_t extends _VApiCreditCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField appId = integer("app");

    public final IntegerField apiId = integer("api");

    public final BigDecimalField credit = bigDecimal("credit");

}
