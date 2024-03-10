package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.SchemaDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PriorityDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    @Override
    public PriorityDef build()
            throws Exception {
        PriorityDef a = new PriorityDef();
        a.setSchema(schema);
        return a;
    }

    @Override
    public PriorityDefSamples wireAny(IRandomPicker picker) {
        this.schema = picker.pickAny(SchemaDefMapper.class, "_schema");
        return this;
    }

    @Override
    public PriorityDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
