package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryReview;
import net.bodz.violet.plan.DiaryReviewSamples;

public class DiaryReviewMapperTest
        extends AbstractMapperTest<DiaryReview, DiaryReviewMask, DiaryReviewMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public DiaryReview buildSample() {
        Diary diary = tables.pickAny(DiaryMapper.class, "diary");
        User user = tables.pickAny(UserMapper.class, "user");
        return DiaryReviewSamples.build(diary, user);
    }

}
