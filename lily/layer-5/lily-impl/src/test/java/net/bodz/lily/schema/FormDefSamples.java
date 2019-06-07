package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class FormDefSamples
        extends TestSamples {

    public static FormDef build(SchemaDef schema) {
        FormDef a = new FormDef();
        a.setSchema(schema);
        a.setLabel("formDef-1");
        a.setDescription("A formDef named formDef-1.");
        return a;
    }

}
