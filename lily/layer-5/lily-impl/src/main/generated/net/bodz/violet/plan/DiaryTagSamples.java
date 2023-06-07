package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public DiaryTag parent;
    public Group ownerGroup;

    public DiaryTag build()
            throws Exception {
        DiaryTag a = new DiaryTag();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
