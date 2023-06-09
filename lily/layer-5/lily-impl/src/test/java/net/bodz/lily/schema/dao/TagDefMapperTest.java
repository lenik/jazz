package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.TagDef;
import net.bodz.lily.schema.TagDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class TagDefMapperTest
        extends AbstractTableTest<TagDef, TagDefMask, TagDefMapper> {

    @Override
    public TagDef buildSample()
            throws Exception {
        TagDefSamples a = new TagDefSamples();
        a.tagGroup = tables.pickAny(TagGroupDefMapper.class, "_tagv");
        return a.build();
    }

}
