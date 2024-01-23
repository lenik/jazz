package net.bodz.violet.art.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _ArtifactVoteCriteriaBuilder_stuff<self_t extends _ArtifactVoteCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField parentId = integer("parent");

    public final IntegerField userId = integer("\"user\"");

    public final IntegerField voteScore = integer("votes");

}
