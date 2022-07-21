package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.Post;
import net.bodz.lily.pub.PostTalk;
import net.bodz.lily.pub.PostTalkSamples;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class PostTalkMapperTest
        extends AbstractMapperTest<PostTalk, PostTalkMask, PostTalkMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PostTalk buildSample() {
        User op = tables.pickAny(UserMapper.class, "user");
        Post post = tables.pickAny(PostMapper.class, "post");
        PostTalk parent = tables.pickAny(PostTalkMapper.class, "post_msg");
        return PostTalkSamples.build(op, post, parent);
    }

}
