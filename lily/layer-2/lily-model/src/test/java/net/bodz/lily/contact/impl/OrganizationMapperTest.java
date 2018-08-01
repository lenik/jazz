package net.bodz.lily.contact.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.OrganizationSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class OrganizationMapperTest
        extends AbstractMapperTest<Organization, OrganizationMask, OrganizationMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Organization buildSample() {
        return OrganizationSamples.build();
    }

}
