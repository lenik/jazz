package net.bodz.lily.schema;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class SchemaDefSamples
        extends TestSampleBuilder {

    @Override
    public SchemaDef build()
            throws Exception {
        SchemaDef a = new SchemaDef();
        a.setId(2100892269);
        a.setCode("a Au Ux icoe paaee&oi");
        a.setDummy(1780359307);
        return a;
    }

    @Override
    public SchemaDefSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public SchemaDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
