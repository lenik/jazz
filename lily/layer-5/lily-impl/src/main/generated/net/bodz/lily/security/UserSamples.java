package net.bodz.lily.security;

import net.bodz.lily.contact.Person;
import net.bodz.lily.test.TestSampleBuilder;

public class UserSamples
        extends TestSampleBuilder {

    public Person person;
    public Group primaryGroup;
    public User referer;
    public UserType type;

    public User build()
            throws Exception {
        User a = new User();
        a.setPerson(person);
        a.setPrimaryGroup(primaryGroup);
        a.setReferer(referer);
        a.setType(type);
        return a;
    }

}
