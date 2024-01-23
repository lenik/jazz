package net.bodz.violet.fab;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabStdTestMapper;
import net.bodz.violet.fab.dao.FabTrackMapper;

public class FabTrackTestSamples
        extends TestSampleBuilder {

    public FabTrack track;
    public FabStdTest standard;

    @Override
    public FabTrackTest build()
            throws Exception {
        FabTrackTest a = new FabTrackTest();
        a.setTrack(track);
        a.setStandard(standard);
        a.setId(9090319591172490544L);
        a.setValid(false);
        return a;
    }

    @Override
    public FabTrackTestSamples wireAny(IRandomPicker picker) {
        this.track = picker.pickAny(FabTrackMapper.class, "fabtrack");
        this.standard = picker.pickAny(FabStdTestMapper.class, "fabstdtest");
        return this;
    }

    @Override
    public FabTrackTest buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
