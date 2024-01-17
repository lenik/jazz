package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleParameterType;
import net.bodz.lily.pub.ArticleParameterTypeSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleParameterTypeMapperTest
        extends AbstractTableTest<ArticleParameterType, ArticleParameterTypeCriteriaBuilder, ArticleParameterTypeMapper> {

    @Override
    public ArticleParameterType buildSample()
            throws Exception {
        ArticleParameterTypeSamples a = new ArticleParameterTypeSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
