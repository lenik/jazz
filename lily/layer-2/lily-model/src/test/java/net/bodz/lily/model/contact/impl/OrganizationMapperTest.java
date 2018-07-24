package net.bodz.lily.model.contact.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.contact.Organization;
import net.bodz.lily.model.contact.OrganizationSamples;
import net.bodz.lily.model.test.AbstractMapperTest;
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
