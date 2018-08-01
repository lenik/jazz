package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.PhaseDef;
import net.bodz.lily.schema.PhaseDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PhaseDefMapperTest
        extends AbstractMapperTest<PhaseDef, PhaseDefMask, PhaseDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PhaseDef buildSample() {
        return PhaseDefSamples.build();
    }

}
