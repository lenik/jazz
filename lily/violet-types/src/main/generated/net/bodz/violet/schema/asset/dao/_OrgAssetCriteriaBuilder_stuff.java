package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.concrete.CoMomentIntervalCriteriaBuilder;

public class _OrgAssetCriteriaBuilder_stuff<self_t extends _OrgAssetCriteriaBuilder_stuff<self_t>>
        extends CoMomentIntervalCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField artifactId = integer("art");

    public final IntegerField regionId = integer("region");

    public final BigDecimalField quantity = bigDecimal("qty");

    public final LongField serial = _long("serial");


}
