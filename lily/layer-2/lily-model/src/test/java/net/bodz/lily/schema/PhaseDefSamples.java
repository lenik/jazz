package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class PhaseDefSamples
        extends TestSamples {

    public static PhaseDef build(SchemaDef schema) {
        PhaseDef a = new PhaseDef();
        a.setSchema(schema);
        a.setLabel("phaseDef-1");
        a.setDescription("A phaseDef named phaseDef-1.");
        return a;
    }

}
