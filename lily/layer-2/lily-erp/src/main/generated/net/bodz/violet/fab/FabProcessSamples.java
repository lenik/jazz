package net.bodz.violet.fab;

import java.math.BigDecimal;

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
        a.setId(3769431772802184892L);
        a.setYear(1501112555);
        a.setQuantity(new BigDecimal("8337941"));
        a.setTrackCount(872550144);
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
