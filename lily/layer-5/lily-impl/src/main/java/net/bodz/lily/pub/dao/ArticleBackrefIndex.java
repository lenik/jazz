package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.ArticleBackref;

/**
* @label ArticleBackref
*/
@ObjectType(ArticleBackref.class)
public class ArticleBackrefIndex
        extends CoIndex<ArticleBackref, ArticleBackrefMask> {

    public ArticleBackrefIndex() {
    }

}
