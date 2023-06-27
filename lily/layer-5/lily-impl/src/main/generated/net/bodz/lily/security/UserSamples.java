package net.bodz.lily.security;

import net.bodz.lily.test.TestSampleBuilder;

public class UserSamples
        extends TestSampleBuilder {

    public Group primaryGroup;
    public User referer;
    public UserType type;

    @Override
    public User build()
            throws Exception {
        User a = new User();
        a.setPrimaryGroup(primaryGroup);
        a.setReferer(referer);
        a.setType(type);
        return a;
    }

}
