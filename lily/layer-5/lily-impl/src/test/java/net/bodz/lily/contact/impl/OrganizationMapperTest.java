package net.bodz.lily.contact.impl;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.OrganizationSamples;
import net.bodz.lily.test.AbstractTableTest;

public class OrganizationMapperTest
        extends AbstractTableTest<Organization, OrganizationMask, OrganizationMapper> {

    @Override
    public Organization buildSample() {
        return OrganizationSamples.build();
    }

}
