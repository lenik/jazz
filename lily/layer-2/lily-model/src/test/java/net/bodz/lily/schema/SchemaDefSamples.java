package net.bodz.lily.schema;

import net.bodz.lily.schema.SchemaDef;

public class SchemaDefSamples {

    public static SchemaDef build() {
        SchemaDef a = new SchemaDef();
        a.setLabel("schemaDef-1");
        a.setDescription("A schemaDef named schemaDef-1.");
        return a;
    }

}
