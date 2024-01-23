package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostCategory;
import net.bodz.lily.pub.PostCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostCategoryMapperTest
        extends AbstractTableTest<PostCategory, PostCategoryMapper> {

    @Override
    public PostCategory buildSample()
            throws Exception {
        PostCategorySamples a = new PostCategorySamples();
        return a.buildWired(tables);
    }

}
