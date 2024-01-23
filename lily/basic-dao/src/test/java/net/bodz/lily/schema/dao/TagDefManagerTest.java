package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.TagDef;
import net.bodz.lily.schema.TagDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class TagDefManagerTest
        extends AbstractManagerTest<TagDef, TagDefMapper, TagDefManager> {

    @Override
    public TagDef buildSample()
            throws Exception {
        TagDefSamples a = new TagDefSamples();
        return a.buildWired(tables);
    }

}
