package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostParameterType;
import net.bodz.lily.pub.PostParameterTypeSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostParameterTypeMapperTest
        extends AbstractTableTest<PostParameterType, PostParameterTypeCriteriaBuilder, PostParameterTypeMapper> {

    @Override
    public PostParameterType buildSample()
            throws Exception {
        PostParameterTypeSamples a = new PostParameterTypeSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
