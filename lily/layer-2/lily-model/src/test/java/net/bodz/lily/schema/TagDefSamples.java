package net.bodz.lily.schema;

import net.bodz.lily.schema.TagDef;

public class TagDefSamples {

    public static TagDef build() {
        TagDef a = new TagDef();
        a.setLabel("tagDef-1");
        a.setDescription("A tagDef named tagDef-1.");
        return a;
    }

}