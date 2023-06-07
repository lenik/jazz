package net.bodz.violet.plan.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryReview;
import net.bodz.violet.plan.DiaryReviewSamples;

public class DiaryReviewMapperTest
        extends AbstractTableTest<DiaryReview, DiaryReviewMask, DiaryReviewMapper> {

    @Override
    public DiaryReview buildSample()
            throws Exception {
        DiaryReviewSamples a = new DiaryReviewSamples();
        a.op = tables.pickAny(UserMapper.class, "user");
        a.diary = tables.pickAny(DiaryMapper.class, "diary");
        a.parent = tables.pickAny(DiaryReviewMapper.class, "diaryrev");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        return a.build();
    }

}
