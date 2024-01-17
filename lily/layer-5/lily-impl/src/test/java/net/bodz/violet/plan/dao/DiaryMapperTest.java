package net.bodz.violet.plan.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiarySamples;

public class DiaryMapperTest
        extends AbstractTableTest<Diary, DiaryCriteriaBuilder, DiaryMapper> {

    @Override
    public Diary buildSample()
            throws Exception {
        DiarySamples a = new DiarySamples();
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.phase = tables.pickAny(DiaryPhaseMapper.class, "diaryphase");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.category = tables.pickAny(DiaryCategoryMapper.class, "diarycat");
        return a.build();
    }

}
