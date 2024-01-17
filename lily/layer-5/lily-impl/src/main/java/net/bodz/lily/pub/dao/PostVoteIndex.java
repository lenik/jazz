package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.PostVote;

/**
* @label PostVote
*/
@ObjectType(PostVote.class)
public class PostVoteIndex
        extends CoIndex<PostVote, PostVoteCriteriaBuilder> {

    public PostVoteIndex() {
    }

}
