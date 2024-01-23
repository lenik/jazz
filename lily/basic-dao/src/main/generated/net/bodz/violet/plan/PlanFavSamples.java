package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.PlanMapper;

public class PlanFavSamples
        extends TestSampleBuilder {

    public Plan plan;
    public User user;

    @Override
    public PlanFav build()
            throws Exception {
        PlanFav a = new PlanFav();
        a.setPlan(plan);
        a.setUser(user);
        return a;
    }

    @Override
    public PlanFavSamples wireAny(IRandomPicker picker) {
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PlanFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
