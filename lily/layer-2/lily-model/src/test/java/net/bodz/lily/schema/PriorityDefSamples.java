package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class PriorityDefSamples
        extends TestSamples {

    public static PriorityDef build(SchemaDef schema) {
        PriorityDef a = new PriorityDef();
        a.setSchema(schema);
        a.setLabel("priorityDef-1");
        a.setDescription("A priorityDef named priorityDef-1.");
        return a;
    }

}
