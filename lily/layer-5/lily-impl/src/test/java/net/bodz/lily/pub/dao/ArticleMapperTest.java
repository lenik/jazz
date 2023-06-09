package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.Article;
import net.bodz.lily.pub.ArticleSamples;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleMapperTest
        extends AbstractTableTest<Article, ArticleMask, ArticleMapper> {

    @Override
    public Article buildSample()
            throws Exception {
        ArticleSamples a = new ArticleSamples();
        a.op = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.category = tables.pickAny(ArticleCategoryMapper.class, "articlecat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        return a.build();
    }

}
