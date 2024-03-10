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
        a.setValid(false);
        a.setValidSince(ZonedDateTime.parse("2024-01-05T20:41:03.103+02:00[Asia/Famagusta]", DateTimes.ISO_ZONED_DATE_TIME));
        a.setValidUntil(ZonedDateTime.parse("2023-12-16T02:29:03.249+02:00[Africa/Khartoum]", DateTimes.ISO_ZONED_DATE_TIME));
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
