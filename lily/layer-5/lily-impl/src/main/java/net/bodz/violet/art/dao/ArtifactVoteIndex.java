package net.bodz.violet.art.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.art.ArtifactVote;

/**
* @label ArtifactVote
*/
@ObjectType(ArtifactVote.class)
public class ArtifactVoteIndex
        extends CoIndex<ArtifactVote, ArtifactVoteCriteriaBuilder> {

    public ArtifactVoteIndex() {
    }

}
