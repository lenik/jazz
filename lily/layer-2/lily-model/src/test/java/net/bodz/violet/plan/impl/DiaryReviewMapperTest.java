package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.DiaryReview;
import net.bodz.violet.plan.DiaryReviewSamples;

public class DiaryReviewMapperTest
        extends AbstractMapperTest<DiaryReview, DiaryReviewMask, DiaryReviewMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public DiaryReview buildSample() {
        return DiaryReviewSamples.build();
    }

}
