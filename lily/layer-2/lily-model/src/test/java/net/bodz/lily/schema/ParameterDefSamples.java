package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class ParameterDefSamples
        extends TestSamples {

    public static ParameterDef build(SchemaDef schema) {
        ParameterDef a = new ParameterDef();
        a.setSchema(schema);
        a.setLabel("parameterDef-1");
        a.setDescription("A parameterDef named parameterDef-1.");
        return a;
    }

}
