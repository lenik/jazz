package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class TagGroupDefSamples
        extends TestSamples {

    public static TagGroupDef build(SchemaDef schema) {
        TagGroupDef a = new TagGroupDef();
        a.setSchema(schema);
        a.setLabel("tagGroupDef-1");
        a.setDescription("A tagGroupDef named tagGroupDef-1.");
        return a;
    }

}
