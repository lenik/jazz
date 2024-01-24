package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostTalk;
import net.bodz.lily.schema.pub.PostTalkSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTalkMapperTest
        extends AbstractTableTest<PostTalk, PostTalkMapper> {

    @Override
    public PostTalk buildSample()
            throws Exception {
        PostTalkSamples a = new PostTalkSamples();
        return a.buildWired(tables);
    }

}
