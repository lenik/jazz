package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.CategoryDef;
import net.bodz.lily.schema.CategoryDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class CategoryDefManagerTest
        extends AbstractManagerTest<CategoryDef, CategoryDefMapper, CategoryDefManager> {

    @Override
    public CategoryDef buildSample()
            throws Exception {
        CategoryDefSamples a = new CategoryDefSamples();
        return a.buildWired(tables);
    }

}
