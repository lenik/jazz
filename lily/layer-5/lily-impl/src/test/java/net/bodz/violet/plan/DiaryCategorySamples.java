package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class DiaryCategorySamples
        extends TestSamples {

    public static DiaryCategory build() {
        DiaryCategory a = new DiaryCategory();
        a.setLabel("diaryCategory-1");
        a.setDescription("A diaryCategory named diaryCategory-1.");
        return a;
    }

}
