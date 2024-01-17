package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostCategory;
import net.bodz.lily.pub.PostCategorySamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostCategoryMapperTest
        extends AbstractTableTest<PostCategory, PostCategoryCriteriaBuilder, PostCategoryMapper> {

    @Override
    public PostCategory buildSample()
            throws Exception {
        PostCategorySamples a = new PostCategorySamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(PostCategoryMapper.class, "postcat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
