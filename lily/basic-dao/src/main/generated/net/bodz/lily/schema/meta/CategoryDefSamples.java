package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.CategoryDefMapper;
import net.bodz.lily.schema.meta.dao.SchemaDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class CategoryDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;
    public CategoryDef parent;

    @Override
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

    @Override
    public CategoryDefSamples wireAny(IRandomPicker picker) {
        this.schema = picker.pickAny(SchemaDefMapper.class, "_schema");
        this.parent = picker.pickAny(CategoryDefMapper.class, "_cat");
        return this;
    }

    @Override
    public CategoryDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
