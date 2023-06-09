package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleFav;
import net.bodz.lily.pub.ArticleFavSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleFavMapperTest
        extends AbstractTableTest<ArticleFav, ArticleFavMask, ArticleFavMapper> {

    @Override
    public ArticleFav buildSample()
            throws Exception {
        ArticleFavSamples a = new ArticleFavSamples();
        a.article = tables.pickAny(ArticleMapper.class, "article");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
