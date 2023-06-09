package net.bodz.lily.pub.dao;

import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.pub.ArticleBackref;
import net.bodz.lily.pub.ArticleBackrefSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleBackrefMapperTest
        extends AbstractTableTest<ArticleBackref, ArticleBackrefMask, ArticleBackrefMapper> {

    @Override
    public ArticleBackref buildSample()
            throws Exception {
        ArticleBackrefSamples a = new ArticleBackrefSamples();
        a.article = tables.pickAny(ArticleMapper.class, "article");
        a.site = tables.pickAny(ExternalSiteMapper.class, "extsite");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
