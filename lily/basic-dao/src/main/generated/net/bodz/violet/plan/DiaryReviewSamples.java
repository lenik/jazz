package net.bodz.violet.plan;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.DiaryMapper;
import net.bodz.violet.plan.dao.DiaryReviewMapper;

public class DiaryReviewSamples
        extends TestSampleBuilder {

    public User op;
    public Diary diary;
    public DiaryReview parent;
    public FormDef form;

    @Override
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

    @Override
    public DiaryReviewSamples wireAny(IRandomPicker picker) {
        this.op = picker.pickAny(UserMapper.class, "user");
        this.diary = picker.pickAny(DiaryMapper.class, "diary");
        this.parent = picker.pickAny(DiaryReviewMapper.class, "diaryrev");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        return this;
    }

    @Override
    public DiaryReview buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
