package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.CategoryDef;
import net.bodz.lily.schema.meta.CategoryDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class CategoryDefMapperTest
        extends AbstractTableTest<CategoryDef, CategoryDefMapper> {

    @Override
    public CategoryDef buildSample()
            throws Exception {
        CategoryDefSamples a = new CategoryDefSamples();
        return a.buildWired(tables);
    }

}
