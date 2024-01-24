package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostBackref;
import net.bodz.lily.schema.pub.PostBackrefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostBackrefMapperTest
        extends AbstractTableTest<PostBackref, PostBackrefMapper> {

    @Override
    public PostBackref buildSample()
            throws Exception {
        PostBackrefSamples a = new PostBackrefSamples();
        return a.buildWired(tables);
    }

}
