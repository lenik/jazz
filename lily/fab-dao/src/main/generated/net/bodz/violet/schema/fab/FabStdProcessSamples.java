package net.bodz.violet.schema.fab;

import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.ArtifactModel;
import net.bodz.violet.schema.art.dao.ArtifactModelMapper;
import net.bodz.violet.schema.fab.dao.FabFnMapper;
import net.bodz.violet.schema.fab.dao.FabStdTestMapper;

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
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setOutput(output);
        a.setTest(test);
        a.setFunction(function);
        a.setId(165373854);
        a.setValid(true);
        a.setValidSince(ZonedDateTime.parse("2023-12-23 09:39:36", DateTimes.D10T8));
        a.setValidUntil(ZonedDateTime.parse("2024-01-24 15:12:58", DateTimes.D10T8));
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
