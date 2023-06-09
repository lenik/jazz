package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.CategoryDef;
import net.bodz.lily.schema.CategoryDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class CategoryDefMapperTest
        extends AbstractTableTest<CategoryDef, CategoryDefMask, CategoryDefMapper> {

    @Override
    public CategoryDef buildSample()
            throws Exception {
        CategoryDefSamples a = new CategoryDefSamples();
        a.schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        a.parent = tables.pickAny(CategoryDefMapper.class, "_cat");
        return a.build();
    }

}
