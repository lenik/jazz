package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.Post;
import net.bodz.violet.pub.PostTalk;
import net.bodz.violet.pub.PostTalkSamples;

public class PostTalkMapperTest
        extends AbstractMapperTest<PostTalk, PostTalkMask, PostTalkMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostTalk buildSample() {
        User op = tables.pickAny(UserMapper.class, "user");
        Post post = tables.pickAny(PostMapper.class, "post");
        PostTalk parent = tables.pickAny(PostTalkMapper.class, "post_msg");
        return PostTalkSamples.build(op, post, parent);
    }

}
