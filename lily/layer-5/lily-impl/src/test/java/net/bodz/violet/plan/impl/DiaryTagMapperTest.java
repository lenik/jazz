package net.bodz.violet.plan.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryTag;
import net.bodz.violet.plan.DiaryTagSamples;

public class DiaryTagMapperTest
        extends AbstractTableTest<DiaryTag, DiaryTagMask, DiaryTagMapper> {

    @Override
    public DiaryTag buildSample() {
        return DiaryTagSamples.build();
    }

}
