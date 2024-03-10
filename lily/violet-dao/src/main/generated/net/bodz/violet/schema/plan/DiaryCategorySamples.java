package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.DiaryCategoryMapper;

public class DiaryCategorySamples
        extends TestSampleBuilder {

    public DiaryCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public DiaryCategory build()
            throws Exception {
        DiaryCategory a = new DiaryCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setName("ipup.");
        return a;
    }

    @Override
    public DiaryCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(DiaryCategoryMapper.class, "diarycat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public DiaryCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
