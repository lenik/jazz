package net.bodz.lily.pub.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.pub.Article;

/**
* @label Article
*/
@ObjectType(Article.class)
public class ArticleIndex
        extends CoIndex<Article, ArticleMask> {

    public ArticleIndex() {
    }

}
