package net.bodz.violet.plan;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryPartySamples
        extends TestSampleBuilder {

    public Person person;
    public Organization org;
    public User user;
    public Diary diary;

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

}
