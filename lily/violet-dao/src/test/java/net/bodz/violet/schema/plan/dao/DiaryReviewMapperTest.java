package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.DiaryReview;
import net.bodz.violet.schema.plan.DiaryReviewSamples;

public class DiaryReviewMapperTest
        extends AbstractTableTest<DiaryReview, DiaryReviewMapper> {

    @Override
    public DiaryReview buildSample()
            throws Exception {
        DiaryReviewSamples a = new DiaryReviewSamples();
        return a.buildWired(tables);
    }

}
