package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.MapperTestSupport;
import net.bodz.violet.pub.PostTalk;

public class PostTalkMapperTest
        extends AbstractMapperTest<PostTalk, PostTalkMask, PostTalkMapper> {

    @Override
    public DataContext getContext() {
        return MapperTestSupport.getDefaultContext();
    }

    @Override
    public PostTalk buildSample() {
        return PostTalkSamples.build();
    }

}
