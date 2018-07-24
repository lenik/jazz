package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
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
        return PostTalkSamples.build();
    }

}
