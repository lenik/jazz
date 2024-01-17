package net.bodz.violet.fab;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.dao.ArtifactModelMapper;
import net.bodz.violet.fab.dao.FabFnMapper;
import net.bodz.violet.fab.dao.FabStdTestMapper;

public class FabStdProcessSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public ArtifactModel output;
    public FabStdTest test;
    public FabFn function;

    @Override
    public FabStdProcess build()
            throws Exception {
        FabStdProcess a = new FabStdProcess();
        a.setId(165373854);
        a.setValid(true);
        a.setDuration(1399266125);
        a.setStrict(false);
        return a;
    }

    @Override
    public FabStdProcessSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.output = picker.pickAny(ArtifactModelMapper.class, "artmodel");
        this.test = picker.pickAny(FabStdTestMapper.class, "fabstdtest");
        this.function = picker.pickAny(FabFnMapper.class, "fabfn");
        return this;
    }

    @Override
    public FabStdProcess buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
