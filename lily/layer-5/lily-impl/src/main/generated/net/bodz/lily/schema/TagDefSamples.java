package net.bodz.lily.schema;

import net.bodz.lily.test.TestSampleBuilder;

public class TagDefSamples
        extends TestSampleBuilder {

    public TagGroupDef tagGroup;

    public TagDef build()
            throws Exception {
        TagDef a = new TagDef();
        a.setTagGroup(tagGroup);
        a.setId(1803516836);
        return a;
    }

}
