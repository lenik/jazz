package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.MapperTestSupport;
import net.bodz.violet.pub.Post;

public class PostMapperTest
        extends AbstractMapperTest<Post, PostMask, PostMapper> {

    @Override
    public DataContext getContext() {
        return MapperTestSupport.getDefaultContext();
    }

    @Override
    public Post buildSample() {
        return PostSamples.build();
    }

}
