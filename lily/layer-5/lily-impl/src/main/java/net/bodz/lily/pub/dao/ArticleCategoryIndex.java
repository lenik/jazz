package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.ArticleCategory;

/**
* @label ArticleCategory
*/
@ObjectType(ArticleCategory.class)
public class ArticleCategoryIndex
        extends CoIndex<ArticleCategory, ArticleCategoryMask> {

    public ArticleCategoryIndex() {
    }

}
