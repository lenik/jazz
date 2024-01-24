package net.bodz.violet.schema.fab;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.fab.dao.FabFnMapper;

public class FabFnSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public FabFn parent;

    @Override
    public FabFn build()
            throws Exception {
        FabFn a = new FabFn();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setId(1916269750);
        a.setCode("pouiw aqu@t'eibju");
        a.setDepth(1782719024);
        a.setRefCount(1266343398);
        return a;
    }

    @Override
    public FabFnSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(FabFnMapper.class, "fabfn");
        return this;
    }

    @Override
    public FabFn buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
