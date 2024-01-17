package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTagType;
import net.bodz.lily.pub.PostTagTypeSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostTagTypeMapperTest
        extends AbstractTableTest<PostTagType, PostTagTypeCriteriaBuilder, PostTagTypeMapper> {

    @Override
    public PostTagType buildSample()
            throws Exception {
        PostTagTypeSamples a = new PostTagTypeSamples();
        a.parent = tables.pickAny(PostTagTypeMapper.class, "posttag");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
