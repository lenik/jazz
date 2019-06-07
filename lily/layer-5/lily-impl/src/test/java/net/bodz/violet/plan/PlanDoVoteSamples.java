package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;

public class PlanDoVoteSamples
        extends TestSamples {

    public static PlanDoVote build(PlanDo planDo, User user) {
        PlanDoVote a = new PlanDoVote();
        a.setPlanDo(planDo);
        a.setUser(user);
        a.setVotes(random.nextInt(3) - 1);
        return a;
    }

}
