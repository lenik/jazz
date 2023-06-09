package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTagType;
import net.bodz.lily.pub.ArticleTagTypeSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTagTypeMapperTest
        extends AbstractTableTest<ArticleTagType, ArticleTagTypeMask, ArticleTagTypeMapper> {

    @Override
    public ArticleTagType buildSample()
            throws Exception {
        ArticleTagTypeSamples a = new ArticleTagTypeSamples();
        a.parent = tables.pickAny(ArticleTagTypeMapper.class, "articletag");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
