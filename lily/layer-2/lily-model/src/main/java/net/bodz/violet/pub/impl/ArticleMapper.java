package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.violet.pub.Article;
import net.bodz.violet.pub.impl.ArticleMask;

/**
 * @mapper.xml ArticleMapper.xml
 */
public interface ArticleMapper
        extends IMapperTemplate<Article, ArticleMask> {

}
