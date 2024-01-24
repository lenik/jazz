package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostCategory;
import net.bodz.lily.schema.pub.PostCategorySamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostCategoryManagerTest
        extends AbstractManagerTest<PostCategory, PostCategoryMapper, PostCategoryManager> {

    @Override
    public PostCategory buildSample()
            throws Exception {
        PostCategorySamples a = new PostCategorySamples();
        return a.buildWired(tables);
    }

}
