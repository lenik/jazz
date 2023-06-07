package net.bodz.violet.plan;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryReviewSamples
        extends TestSampleBuilder {

    public User op;
    public Diary diary;
    public DiaryReview parent;
    public FormDef form;

    public DiaryReview build()
            throws Exception {
        DiaryReview a = new DiaryReview();
        a.setOp(op);
        a.setDiary(diary);
        a.setParent(parent);
        a.setForm(form);
        a.setFormArguments("kv Ktcp; iduownen; wfueo; y'jv ifu tnam i fgrg uq eex; opu. Iez&mmi uae&qtuu'uec Aoci htoo, ia uiou oiu, mjo iod, pda Euh axpev? ituz*cxaw@u, nuea ixquu");
        return a;
    }

}
