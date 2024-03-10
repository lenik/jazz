package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.SchemaDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ParameterDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    @Override
    public ParameterDef build()
            throws Exception {
        ParameterDef a = new ParameterDef();
        a.setSchema(schema);
        return a;
    }

    @Override
    public ParameterDefSamples wireAny(IRandomPicker picker) {
        this.schema = picker.pickAny(SchemaDefMapper.class, "_schema");
        return this;
    }

    @Override
    public ParameterDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
