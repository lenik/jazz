package net.bodz.violet.plan;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class DiaryPartySamples
        extends TestSamples {

    public static DiaryParty build(Diary diary, User user, Person person, Organization org) {
        DiaryParty a = new DiaryParty();
        a.setLabel("diaryParty-1");
        a.setDescription("A diaryParty named diaryParty-1.");
        a.setDiary(diary);
        a.setRole("role1.");
        a.setValue(random.nextInt(10000));
        switch (random.nextInt(3)) {
        case 0:
            a.setUser(user);
            break;
        case 1:
            a.setPerson(person);
            break;
        case 2:
            a.setOrg(org);
        }
        return a;
    }

}
