package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class CategoryDefSamples
        extends TestSamples {

    public static CategoryDef build(SchemaDef schema) {
        CategoryDef a = new CategoryDef();
        a.setSchema(schema);
        a.setLabel("categoryDef-1");
        a.setDescription("A categoryDef named categoryDef-1.");
        return a;
    }

}
