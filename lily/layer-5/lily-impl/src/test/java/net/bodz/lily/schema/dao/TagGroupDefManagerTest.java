package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.TagGroupDef;
import net.bodz.lily.schema.TagGroupDefSamples;
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
