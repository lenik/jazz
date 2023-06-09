package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleCategory;
import net.bodz.lily.pub.ArticleCategorySamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleCategoryMapperTest
        extends AbstractTableTest<ArticleCategory, ArticleCategoryMask, ArticleCategoryMapper> {

    @Override
    public ArticleCategory buildSample()
            throws Exception {
        ArticleCategorySamples a = new ArticleCategorySamples();
        a.parent = tables.pickAny(ArticleCategoryMapper.class, "articlecat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
