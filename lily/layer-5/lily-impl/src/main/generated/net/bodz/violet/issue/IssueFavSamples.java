package net.bodz.violet.issue;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssueFavSamples
        extends TestSampleBuilder {

    public User user;
    public Issue issue;

    public IssueFav build()
            throws Exception {
        IssueFav a = new IssueFav();
        a.setUser(user);
        a.setIssue(issue);
        return a;
    }

}
