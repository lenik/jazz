package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.OrgUnitSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class OrgUnitManagerTest
        extends AbstractManagerTest<OrgUnit, OrgUnitMapper, OrgUnitManager> {

    @Override
    public OrgUnit buildSample()
            throws Exception {
        OrgUnitSamples a = new OrgUnitSamples();
        return a.buildWired(tables);
    }

}
