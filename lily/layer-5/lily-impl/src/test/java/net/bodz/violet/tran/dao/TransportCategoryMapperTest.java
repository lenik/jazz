package net.bodz.violet.tran.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportCategory;
import net.bodz.violet.tran.TransportCategorySamples;

public class TransportCategoryMapperTest
        extends AbstractTableTest<TransportCategory, TransportCategoryCriteriaBuilder, TransportCategoryMapper> {

    @Override
    public TransportCategory buildSample()
            throws Exception {
        TransportCategorySamples a = new TransportCategorySamples();
        a.parent = tables.pickAny(TransportCategoryMapper.class, "trancat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
