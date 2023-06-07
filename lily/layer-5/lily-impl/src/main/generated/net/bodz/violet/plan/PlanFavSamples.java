package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanFavSamples
        extends TestSampleBuilder {

    public Plan plan;
    public User user;

    public PlanFav build()
            throws Exception {
        PlanFav a = new PlanFav();
        a.setPlan(plan);
        a.setUser(user);
        return a;
    }

}
