package net.bodz.violet.fab;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.ArtifactModel;
import net.bodz.violet.art.dao.ArtifactModelMapper;
import net.bodz.violet.fab.dao.FabProcessMapper;
import net.bodz.violet.fab.dao.FabStdProcessMapper;
import net.bodz.violet.fab.dao.FabTaskMapper;

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
        a.setId(3769431772802184892L);
        a.setBeginTime(ZonedDateTime.parse("2024-01-11 13:14:26", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2023-12-14 10:01:27", DateTimes.D10T8));
        a.setYear(1490317106);
        a.setQuantity(new BigDecimal("794135720883938031.70"));
        a.setTrackCount(1955095956);
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
