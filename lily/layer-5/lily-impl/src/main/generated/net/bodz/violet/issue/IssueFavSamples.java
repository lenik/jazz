package net.bodz.violet.issue;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.issue.dao.IssueMapper;

public class IssueFavSamples
        extends TestSampleBuilder {

    public User user;
    public Issue issue;

    @Override
    public IssueFav build()
            throws Exception {
        IssueFav a = new IssueFav();
        a.setUser(user);
        a.setIssue(issue);
        return a;
    }

    @Override
    public IssueFavSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.issue = picker.pickAny(IssueMapper.class, "issue");
        return this;
    }

    @Override
    public IssueFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
