package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.TagGroupDef;
import net.bodz.lily.schema.TagGroupDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class TagGroupDefMapperTest
        extends AbstractTableTest<TagGroupDef, TagGroupDefMask, TagGroupDefMapper> {

    @Override
    public TagGroupDef buildSample()
            throws Exception {
        TagGroupDefSamples a = new TagGroupDefSamples();
        a.schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return a.build();
    }

}
