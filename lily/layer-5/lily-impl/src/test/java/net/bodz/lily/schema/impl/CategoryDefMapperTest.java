package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.CategoryDef;
import net.bodz.lily.schema.CategoryDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractTableTest;

public class CategoryDefMapperTest
        extends AbstractTableTest<CategoryDef, CategoryDefMask, CategoryDefMapper> {

    @Override
    public CategoryDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return CategoryDefSamples.build(schema);
    }

}
