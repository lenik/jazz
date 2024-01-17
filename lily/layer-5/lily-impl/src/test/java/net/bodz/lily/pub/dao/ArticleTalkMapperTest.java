package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTalk;
import net.bodz.lily.pub.ArticleTalkSamples;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTalkMapperTest
        extends AbstractTableTest<ArticleTalk, ArticleTalkCriteriaBuilder, ArticleTalkMapper> {

    @Override
    public ArticleTalk buildSample()
            throws Exception {
        ArticleTalkSamples a = new ArticleTalkSamples();
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.article = tables.pickAny(ArticleMapper.class, "article");
        a.parent = tables.pickAny(ArticleTalkMapper.class, "article_msg");
        a.op = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
