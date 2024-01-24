package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.plan.DiaryReview;
import net.bodz.violet.schema.plan.DiaryReviewSamples;

public class DiaryReviewManagerTest
        extends AbstractManagerTest<DiaryReview, DiaryReviewMapper, DiaryReviewManager> {

    @Override
    public DiaryReview buildSample()
            throws Exception {
        DiaryReviewSamples a = new DiaryReviewSamples();
        return a.buildWired(tables);
    }

}
