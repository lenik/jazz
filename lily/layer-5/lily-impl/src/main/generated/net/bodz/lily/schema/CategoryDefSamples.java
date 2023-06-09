package net.bodz.lily.schema;

import net.bodz.lily.test.TestSampleBuilder;

public class CategoryDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;
    public CategoryDef parent;

    public CategoryDef build()
            throws Exception {
        CategoryDef a = new CategoryDef();
        a.setSchema(schema);
        a.setParent(parent);
        a.setId(347155994);
        a.setCode("hi kai_i aup; au j? sszcm");
        a.setDepth(71728980);
        a.setRefCount(37777407);
        return a;
    }

}
