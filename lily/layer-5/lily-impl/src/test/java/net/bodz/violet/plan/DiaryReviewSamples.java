package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class DiaryReviewSamples
        extends TestSamples {

    public static DiaryReview build(Diary diary, User op) {
        DiaryReview a = new DiaryReview();
        a.setSubject("diaryReview-1");
        a.setRawText("A diaryReview named diaryReview-1.");
        a.setDiary(diary);
        a.setOp(op);
        return a;
    }

}
