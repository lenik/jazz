package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanVoteSamples
        extends TestSampleBuilder {

    public User user;
    public Plan parent;

    public PlanVote build()
            throws Exception {
        PlanVote a = new PlanVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(663069286);
        return a;
    }

}
