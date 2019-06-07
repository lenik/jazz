package net.bodz.violet.plan.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.OrganizationMapper;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryParty;
import net.bodz.violet.plan.DiaryPartySamples;

public class DiaryPartyMapperTest
        extends AbstractMapperTest<DiaryParty, DiaryPartyMask, DiaryPartyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public DiaryParty buildSample() {
        Diary diary = tables.pickAny(DiaryMapper.class, "diary");
        User user = tables.pickAny(UserMapper.class, "user");
        Person person = tables.pickAny(PersonMapper.class, "person");
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        return DiaryPartySamples.build(diary, user, person, org);
    }

}
