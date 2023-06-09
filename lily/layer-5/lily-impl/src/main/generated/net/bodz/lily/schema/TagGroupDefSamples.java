package net.bodz.lily.schema;

import net.bodz.lily.test.TestSampleBuilder;

public class TagGroupDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    public TagGroupDef build()
            throws Exception {
        TagGroupDef a = new TagGroupDef();
        a.setSchema(schema);
        a.setId(1125460359);
        a.setCode("otaxiw");
        a.setForTopic(false);
        a.setForReply(true);
        return a;
    }

}
