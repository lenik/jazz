package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.TagDef;
import net.bodz.lily.schema.meta.TagDefSamples;
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
