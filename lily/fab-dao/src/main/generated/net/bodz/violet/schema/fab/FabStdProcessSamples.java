package net.bodz.violet.schema.fab;

import java.time.OffsetDateTime;

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
        a.setValidSince(OffsetDateTime.parse("2024-01-05T06:46:03.103-11:55", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setValidUntil(OffsetDateTime.parse("2024-01-24T21:52:58.332+03:40", DateTimes.ISO_OFFSET_DATE_TIME));
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
