package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.TagGroupDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class TagDefSamples
        extends TestSampleBuilder {

    public TagGroupDef tagGroup;

    @Override
    public TagDef build()
            throws Exception {
        TagDef a = new TagDef();
        a.setTagGroup(tagGroup);
        return a;
    }

    @Override
    public TagDefSamples wireAny(IRandomPicker picker) {
        this.tagGroup = picker.pickAny(TagGroupDefMapper.class, "_tagv");
        return this;
    }

    @Override
    public TagDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
