package net.bodz.lily.contact.impl;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.OrgUnitSamples;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.test.AbstractMapperTest;

public class OrgUnitMapperTest
        extends AbstractMapperTest<OrgUnit, OrgUnitMask, OrgUnitMapper> {

    @Override
    public OrgUnit buildSample() {
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        OrgUnit parent = tables.pickAny(OrgUnitMapper.class, "orgunit");
        return OrgUnitSamples.build(org, parent);
    }

}
