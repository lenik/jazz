package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.TagDef;
import net.bodz.lily.schema.TagDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class TagDefMapperTest
        extends AbstractMapperTest<TagDef, TagDefMask, TagDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TagDef buildSample() {
        return TagDefSamples.build();
    }

}
