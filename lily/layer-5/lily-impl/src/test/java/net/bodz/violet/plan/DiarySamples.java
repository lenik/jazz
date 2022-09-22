package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class DiarySamples
        extends TestSamples {

    public static Diary build(User op, DiaryCategory category, DiaryPhase phase) {
        Diary a = new Diary();
        a.setSubject("diary-1");
        a.setRawText("A diary named diary-1.");
        a.setOp(op);
        a.setCategory(category);
        a.setPhase(phase);
        return a;
    }

}
