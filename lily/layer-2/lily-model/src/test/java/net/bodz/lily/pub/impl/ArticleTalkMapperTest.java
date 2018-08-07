package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.Article;
import net.bodz.lily.pub.ArticleTalk;
import net.bodz.lily.pub.ArticleTalkSamples;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ArticleTalkMapperTest
        extends AbstractMapperTest<ArticleTalk, ArticleTalkMask, ArticleTalkMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ArticleTalk buildSample() {
        Article article = tables.pickAny(ArticleMapper.class, "article");
        User user = tables.pickAny(UserMapper.class, "user");
        return ArticleTalkSamples.build(article, user);
    }

}
