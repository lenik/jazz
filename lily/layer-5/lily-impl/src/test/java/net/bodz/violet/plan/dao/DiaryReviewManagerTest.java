package net.bodz.violet.plan.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.plan.DiaryReview;
import net.bodz.violet.plan.DiaryReviewSamples;

public class DiaryReviewManagerTest
        extends AbstractManagerTest<DiaryReview, DiaryReviewMapper, DiaryReviewManager> {

    @Override
    public DiaryReview buildSample()
            throws Exception {
        DiaryReviewSamples a = new DiaryReviewSamples();
        return a.buildWired(tables);
    }

}
