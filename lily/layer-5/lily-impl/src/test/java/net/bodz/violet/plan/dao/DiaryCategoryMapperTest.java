package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryCategory;
import net.bodz.violet.plan.DiaryCategorySamples;

public class DiaryCategoryMapperTest
        extends AbstractTableTest<DiaryCategory, DiaryCategoryMask, DiaryCategoryMapper> {

    @Override
    public DiaryCategory buildSample()
            throws Exception {
        DiaryCategorySamples a = new DiaryCategorySamples();
        a.parent = tables.pickAny(DiaryCategoryMapper.class, "diarycat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
