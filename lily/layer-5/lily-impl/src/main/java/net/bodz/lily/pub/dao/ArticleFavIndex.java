package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.ArticleFav;

/**
* @label ArticleFav
*/
@ObjectType(ArticleFav.class)
public class ArticleFavIndex
        extends CoIndex<ArticleFav, ArticleFavCriteriaBuilder> {

    public ArticleFavIndex() {
    }

}
