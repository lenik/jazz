package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class DiaryTagSamples
        extends TestSamples {

    public static DiaryTag build() {
        DiaryTag a = new DiaryTag();
        a.setLabel("diaryTag-1");
        a.setDescription("A diaryTag named diaryTag-1.");
        return a;
    }

}
