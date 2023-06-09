package net.bodz.lily.schema;

import net.bodz.lily.test.TestSampleBuilder;

public class PhaseDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    public PhaseDef build()
            throws Exception {
        PhaseDef a = new PhaseDef();
        a.setSchema(schema);
        a.setId(1171680256);
        a.setCode("Uuuif'xeuib.");
        return a;
    }

}
