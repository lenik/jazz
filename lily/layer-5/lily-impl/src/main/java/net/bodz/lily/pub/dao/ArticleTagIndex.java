package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.ArticleTag;

/**
* @label ArticleTag
*/
@ObjectType(ArticleTag.class)
public class ArticleTagIndex
        extends CoIndex<ArticleTag, ArticleTagMask> {

    public ArticleTagIndex() {
    }

}
