package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanDoVoteSamples
        extends TestSampleBuilder {

    public User user;
    public PlanDo parent;

    public PlanDoVote build()
            throws Exception {
        PlanDoVote a = new PlanDoVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(1361459958);
        return a;
    }

}
