package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.PlanDoMapper;

public class PlanDoVoteSamples
        extends TestSampleBuilder {

    public User user;
    public PlanDo parent;

    @Override
    public PlanDoVote build()
            throws Exception {
        PlanDoVote a = new PlanDoVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(1361459958);
        return a;
    }

    @Override
    public PlanDoVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(PlanDoMapper.class, "plando");
        return this;
    }

    @Override
    public PlanDoVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
