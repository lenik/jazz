package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class PlanVoteSamples
        extends TestSamples {

    public static PlanVote build(Plan plan, User user) {
        PlanVote a = new PlanVote();
        a.setPlan(plan);
        a.setUser(user);
        a.setVotes(random.nextInt(3) - 1);
        return a;
    }

}
