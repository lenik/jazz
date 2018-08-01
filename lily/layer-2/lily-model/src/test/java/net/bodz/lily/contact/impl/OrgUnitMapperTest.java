package net.bodz.lily.contact.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.OrgUnitSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class OrgUnitMapperTest
        extends AbstractMapperTest<OrgUnit, OrgUnitMask, OrgUnitMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public OrgUnit buildSample() {
        return OrgUnitSamples.build();
    }

}
