package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.TagGroupDef;
import net.bodz.lily.schema.meta.TagGroupDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class TagGroupDefManagerTest
        extends AbstractManagerTest<TagGroupDef, TagGroupDefMapper, TagGroupDefManager> {

    @Override
    public TagGroupDef buildSample()
            throws Exception {
        TagGroupDefSamples a = new TagGroupDefSamples();
        return a.buildWired(tables);
    }

}
