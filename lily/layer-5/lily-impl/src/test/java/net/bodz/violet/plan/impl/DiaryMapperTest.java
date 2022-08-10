package net.bodz.violet.plan.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryCategory;
import net.bodz.violet.plan.DiaryPhase;
import net.bodz.violet.plan.DiarySamples;

public class DiaryMapperTest
        extends AbstractMapperTest<Diary, DiaryMask, DiaryMapper> {

    @Override
    public Diary buildSample() {
        User user = tables.pickAny(UserMapper.class, "user");
        DiaryCategory category = tables.pickAny(DiaryCategoryMapper.class, "diarycat");
        DiaryPhase phase = tables.pickAny(DiaryPhaseMapper.class, "diaryphase");
        return DiarySamples.build(user, category, phase);
    }

}
