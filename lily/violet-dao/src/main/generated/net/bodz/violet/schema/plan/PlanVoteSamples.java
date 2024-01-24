package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.PlanMapper;

public class PlanVoteSamples
        extends TestSampleBuilder {

    public User user;
    public Plan parent;

    @Override
    public PlanVote build()
            throws Exception {
        PlanVote a = new PlanVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(663069286);
        return a;
    }

    @Override
    public PlanVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(PlanMapper.class, "plan");
        return this;
    }

    @Override
    public PlanVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
