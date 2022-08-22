package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostCategory;
import net.bodz.lily.pub.PostCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostCategoryMapperTest
        extends AbstractTableTest<PostCategory, PostCategoryMask, PostCategoryMapper> {

    @Override
    public PostCategory buildSample() {
        return PostCategorySamples.build();
    }

}
