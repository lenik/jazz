package net.bodz.violet.plan.dao;

import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryParty;
import net.bodz.violet.plan.DiaryPartySamples;

public class DiaryPartyMapperTest
        extends AbstractTableTest<DiaryParty, DiaryPartyCriteriaBuilder, DiaryPartyMapper> {

    @Override
    public DiaryParty buildSample()
            throws Exception {
        DiaryPartySamples a = new DiaryPartySamples();
        a.person = tables.pickAny(PersonMapper.class, "person");
        a.org = tables.pickAny(OrganizationMapper.class, "org");
        a.user = tables.pickAny(UserMapper.class, "user");
        a.diary = tables.pickAny(DiaryMapper.class, "diary");
        return a.build();
    }

}
