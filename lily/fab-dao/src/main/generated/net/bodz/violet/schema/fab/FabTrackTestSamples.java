package net.bodz.violet.schema.fab;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.fab.dao.FabStdTestMapper;
import net.bodz.violet.schema.fab.dao.FabTrackMapper;

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
        a.setValid(true);
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
