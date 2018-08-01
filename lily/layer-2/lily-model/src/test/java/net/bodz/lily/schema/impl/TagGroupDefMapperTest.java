package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.TagGroupDef;
import net.bodz.lily.schema.TagGroupDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class TagGroupDefMapperTest
        extends AbstractMapperTest<TagGroupDef, TagGroupDefMask, TagGroupDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TagGroupDef buildSample() {
        return TagGroupDefSamples.build();
    }

}
