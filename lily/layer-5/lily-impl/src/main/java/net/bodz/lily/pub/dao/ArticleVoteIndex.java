package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.ArticleVote;

/**
* @label ArticleVote
*/
@ObjectType(ArticleVote.class)
public class ArticleVoteIndex
        extends CoIndex<ArticleVote, ArticleVoteMask> {

    public ArticleVoteIndex() {
    }

}
