package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.schema.TagGroupDef;
import net.bodz.lily.schema.TagGroupDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class TagGroupDefMapperTest
        extends AbstractTableTest<TagGroupDef, TagGroupDefMask, TagGroupDefMapper> {

    @Override
    public TagGroupDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return TagGroupDefSamples.build(schema);
    }

}
