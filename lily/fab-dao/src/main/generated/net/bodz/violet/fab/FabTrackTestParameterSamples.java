package net.bodz.violet.fab;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabStdTestParameterMapper;
import net.bodz.violet.fab.dao.FabStdTesterMapper;
import net.bodz.violet.fab.dao.FabTrackTestMapper;

public class FabTrackTestParameterSamples
        extends TestSampleBuilder {

    public FabStdTestParameter parameter;
    public FabStdTester tester;
    public FabTrackTest test;

    @Override
    public FabTrackTestParameter build()
            throws Exception {
        FabTrackTestParameter a = new FabTrackTestParameter();
        a.setParameter(parameter);
        a.setTester(tester);
        a.setTest(test);
        a.setId(6449731566696903729L);
        a.setActual("Xehakb");
        a.setValid(false);
        return a;
    }

    @Override
    public FabTrackTestParameterSamples wireAny(IRandomPicker picker) {
        this.parameter = picker.pickAny(FabStdTestParameterMapper.class, "fabstdtest_parm");
        this.tester = picker.pickAny(FabStdTesterMapper.class, "fabstdtester");
        this.test = picker.pickAny(FabTrackTestMapper.class, "fabtrack_test");
        return this;
    }

    @Override
    public FabTrackTestParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
