package net.bodz.lily.schema;

import net.bodz.lily.test.TestSamples;

public class TagDefSamples
        extends TestSamples {

    public static TagDef build(SchemaDef schema, TagGroupDef tagv) {
        TagDef a = new TagDef();
        a.setSchema(schema);
        a.setTagGroup(tagv);
        a.setLabel("tagDef-1");
        a.setDescription("A tagDef named tagDef-1.");
        return a;
    }

}
