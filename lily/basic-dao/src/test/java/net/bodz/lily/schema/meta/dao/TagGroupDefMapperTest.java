package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.TagGroupDef;
import net.bodz.lily.schema.meta.TagGroupDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class TagGroupDefMapperTest
        extends AbstractTableTest<TagGroupDef, TagGroupDefMapper> {

    @Override
    public TagGroupDef buildSample()
            throws Exception {
        TagGroupDefSamples a = new TagGroupDefSamples();
        return a.buildWired(tables);
    }

}
