package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.CategoryDef;
import net.bodz.lily.schema.CategoryDefSamples;
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
