package net.bodz.violet.plan.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryReview;
import net.bodz.violet.plan.DiaryReviewSamples;

public class DiaryReviewMapperTest
        extends AbstractTableTest<DiaryReview, DiaryReviewMask, DiaryReviewMapper> {

    @Override
    public DiaryReview buildSample() {
        Diary diary = tables.pickAny(DiaryMapper.class, "diary");
        User user = tables.pickAny(UserMapper.class, "user");
        return DiaryReviewSamples.build(diary, user);
    }

}
