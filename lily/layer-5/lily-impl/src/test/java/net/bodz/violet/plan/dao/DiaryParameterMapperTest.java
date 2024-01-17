package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryParameter;
import net.bodz.violet.plan.DiaryParameterSamples;

public class DiaryParameterMapperTest
        extends AbstractTableTest<DiaryParameter, DiaryParameterCriteriaBuilder, DiaryParameterMapper> {

    @Override
    public DiaryParameter buildSample()
            throws Exception {
        DiaryParameterSamples a = new DiaryParameterSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
