package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.schema.TagDef;
import net.bodz.lily.schema.TagDefSamples;
import net.bodz.lily.schema.TagGroupDef;
import net.bodz.lily.test.AbstractTableTest;

public class TagDefMapperTest
        extends AbstractTableTest<TagDef, TagDefMask, TagDefMapper> {

    @Override
    public TagDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        TagGroupDef tagv = tables.pickAny(TagGroupDefMapper.class, "_tagv");
        return TagDefSamples.build(schema, tagv);
    }

}
