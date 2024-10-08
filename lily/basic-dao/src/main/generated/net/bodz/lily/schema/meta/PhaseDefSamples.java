package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.SchemaDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PhaseDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    @Override
    public PhaseDef build()
            throws Exception {
        PhaseDef a = new PhaseDef();
        a.setSchema(schema);
        a.setCode("Uuuif'xeuib.");
        return a;
    }

    @Override
    public PhaseDefSamples wireAny(IRandomPicker picker) {
        this.schema = picker.pickAny(SchemaDefMapper.class, "_schema");
        return this;
    }

    @Override
    public PhaseDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
