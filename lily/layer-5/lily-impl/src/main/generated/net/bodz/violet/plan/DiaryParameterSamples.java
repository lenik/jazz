package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryParameterSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public DiaryParameter build()
            throws Exception {
        DiaryParameter a = new DiaryParameter();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setDummy(807287720);
        return a;
    }

}
