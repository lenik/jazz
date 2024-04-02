package net.bodz.violet.schema.art.dao;

import net.bodz.lily.concrete.VoteRecordCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.art.ArtifactVote;

@ForEntityType(ArtifactVote.class)
public class _ArtifactVoteCriteriaBuilder_stuff<self_t extends _ArtifactVoteCriteriaBuilder_stuff<self_t>>
        extends VoteRecordCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField parentId = integer("parent");

    public final IntegerField voteScore = integer("votes");

}
