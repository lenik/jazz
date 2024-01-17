package net.bodz.violet.plan;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.DiaryMapper;

public class DiaryPartySamples
        extends TestSampleBuilder {

    public Person person;
    public Organization org;
    public User user;
    public Diary diary;

    @Override
    public DiaryParty build()
            throws Exception {
        DiaryParty a = new DiaryParty();
        a.setPerson(person);
        a.setOrg(org);
        a.setUser(user);
        a.setDiary(diary);
        a.setId(5427017799090752076L);
        a.setValue(2033405508);
        return a;
    }

    @Override
    public DiaryPartySamples wireAny(IRandomPicker picker) {
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.org = picker.pickAny(OrganizationMapper.class, "org");
        this.user = picker.pickAny(UserMapper.class, "user");
        this.diary = picker.pickAny(DiaryMapper.class, "diary");
        return this;
    }

    @Override
    public DiaryParty buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
