package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTalk;
import net.bodz.lily.pub.PostTalkSamples;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostTalkMapperTest
        extends AbstractTableTest<PostTalk, PostTalkCriteriaBuilder, PostTalkMapper> {

    @Override
    public PostTalk buildSample()
            throws Exception {
        PostTalkSamples a = new PostTalkSamples();
        a.post = tables.pickAny(PostMapper.class, "post");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.parent = tables.pickAny(PostTalkMapper.class, "post_msg");
        a.op = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
