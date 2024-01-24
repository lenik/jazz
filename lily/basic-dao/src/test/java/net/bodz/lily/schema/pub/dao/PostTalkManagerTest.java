package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostTalk;
import net.bodz.lily.schema.pub.PostTalkSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostTalkManagerTest
        extends AbstractManagerTest<PostTalk, PostTalkMapper, PostTalkManager> {

    @Override
    public PostTalk buildSample()
            throws Exception {
        PostTalkSamples a = new PostTalkSamples();
        return a.buildWired(tables);
    }

}
