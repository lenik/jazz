package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class SchemaDefSamples
        extends TestSamples {

    public static SchemaDef build() {
        SchemaDef a = new SchemaDef();
        a.setLabel("schemaDef-1");
        a.setDescription("A schemaDef named schemaDef-1.");
        return a;
    }

}
