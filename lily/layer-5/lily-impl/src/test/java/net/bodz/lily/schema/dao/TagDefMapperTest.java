package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.TagDef;
import net.bodz.lily.schema.TagDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class TagDefMapperTest
        extends AbstractTableTest<TagDef, TagDefMapper> {

    @Override
    public TagDef buildSample()
            throws Exception {
        TagDefSamples a = new TagDefSamples();
        return a.buildWired(tables);
    }

}
