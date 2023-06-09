package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.Post;
import net.bodz.lily.pub.PostSamples;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostMapperTest
        extends AbstractTableTest<Post, PostMask, PostMapper> {

    @Override
    public Post buildSample()
            throws Exception {
        PostSamples a = new PostSamples();
        a.parent = tables.pickAny(PostMapper.class, "post");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.category = tables.pickAny(PostCategoryMapper.class, "postcat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
