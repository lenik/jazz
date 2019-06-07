package net.bodz.violet.plan;

import net.bodz.lily.test.TestSamples;

public class DiaryParameterSamples
        extends TestSamples {

    public static DiaryParameter build() {
        DiaryParameter a = new DiaryParameter();
        a.setLabel("diaryParameter-1");
        a.setDescription("A diaryParameter named diaryParameter-1.");
        return a;
    }

}
