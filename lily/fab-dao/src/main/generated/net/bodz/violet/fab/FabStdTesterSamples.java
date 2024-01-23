package net.bodz.violet.fab;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class FabStdTesterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public FabStdTester build()
            throws Exception {
        FabStdTester a = new FabStdTester();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setId(1042437229);
        a.setCmdline("ulez vb&za mt Otue io aiyi we aep ivru poouv, ul zaf, waoa, auaw'lu epezqu@Eos aodhuuz.");
        return a;
    }

    @Override
    public FabStdTesterSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public FabStdTester buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
