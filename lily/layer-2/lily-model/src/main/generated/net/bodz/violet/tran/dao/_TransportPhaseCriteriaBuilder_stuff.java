package net.bodz.violet.tran.dao;

import net.bodz.lily.model.base.CoCodeCriteriaBuilder;

public class _TransportPhaseCriteriaBuilder_stuff<self_t extends _TransportPhaseCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
