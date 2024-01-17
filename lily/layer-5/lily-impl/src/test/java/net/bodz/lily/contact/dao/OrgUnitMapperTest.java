package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.OrgUnitSamples;
import net.bodz.lily.test.AbstractTableTest;

public class OrgUnitMapperTest
        extends AbstractTableTest<OrgUnit, OrgUnitMapper> {

    @Override
    public OrgUnit buildSample()
            throws Exception {
        OrgUnitSamples a = new OrgUnitSamples();
        return a.buildWired(tables);
    }

}
