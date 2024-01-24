package net.bodz.lily.schema.meta;

import net.bodz.lily.schema.meta.dao.SchemaDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class TagGroupDefSamples
        extends TestSampleBuilder {

    public SchemaDef schema;

    @Override
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

    @Override
    public TagGroupDefSamples wireAny(IRandomPicker picker) {
        this.schema = picker.pickAny(SchemaDefMapper.class, "_schema");
        return this;
    }

    @Override
    public TagGroupDef buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
