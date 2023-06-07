package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryTag;
import net.bodz.violet.plan.DiaryTagSamples;

public class DiaryTagMapperTest
        extends AbstractTableTest<DiaryTag, DiaryTagMask, DiaryTagMapper> {

    @Override
    public DiaryTag buildSample()
            throws Exception {
        DiaryTagSamples a = new DiaryTagSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(DiaryTagMapper.class, "diarytag");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
