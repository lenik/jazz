package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryCategorySamples
        extends TestSampleBuilder {

    public DiaryCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    public DiaryCategory build()
            throws Exception {
        DiaryCategory a = new DiaryCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
