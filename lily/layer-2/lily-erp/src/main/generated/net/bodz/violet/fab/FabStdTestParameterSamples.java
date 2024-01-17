package net.bodz.violet.fab;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabStdTestMapper;

public class FabStdTestParameterSamples
        extends TestSampleBuilder {

    public FabStdTest test;

    @Override
    public FabStdTestParameter build()
            throws Exception {
        FabStdTestParameter a = new FabStdTestParameter();
        a.setTest(test);
        a.setId(1390277138);
        a.setRequired(true);
        a.setExpected("dzoh nijqii@fqq ayof");
        return a;
    }

    @Override
    public FabStdTestParameterSamples wireAny(IRandomPicker picker) {
        this.test = picker.pickAny(FabStdTestMapper.class, "fabstdtest");
        return this;
    }

    @Override
    public FabStdTestParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
