package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
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
import net.bodz.violet.schema.fab.dao.FabProcessMapper;
import net.bodz.violet.schema.fab.dao.FabStdProcessMapper;
import net.bodz.violet.schema.fab.dao.FabTaskMapper;

public class FabProcessSamples
        extends TestSampleBuilder {

    public FabStdProcess standard;
    public User ownerUser;
    public FabProcess parent;
    public ArtifactModel output;
    public Group ownerGroup;
    public FabTask task;

    @Override
    public FabProcess build()
            throws Exception {
        FabProcess a = new FabProcess();
        a.setStandard(standard);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOutput(output);
        a.setOwnerGroup(ownerGroup);
        a.setTask(task);
        a.setQuantity(new BigDecimal("69.71"));
        a.setSince(OffsetDateTime.parse("2024-01-23T05:14:14.679-05:33", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setDeadline(OffsetDateTime.parse("2024-01-11T20:14:52.862-06:34", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setTrackCount(2035351287);
        return a;
    }

    @Override
    public FabProcessSamples wireAny(IRandomPicker picker) {
        this.standard = picker.pickAny(FabStdProcessMapper.class, "fabstdproc");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(FabProcessMapper.class, "fabproc");
        this.output = picker.pickAny(ArtifactModelMapper.class, "artmodel");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.task = picker.pickAny(FabTaskMapper.class, "fabtask");
        return this;
    }

    @Override
    public FabProcess buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
