package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.OrganizationSamples;
import net.bodz.lily.test.AbstractTableTest;

public class OrganizationMapperTest
        extends AbstractTableTest<Organization, OrganizationMapper> {

    @Override
    public Organization buildSample()
            throws Exception {
        OrganizationSamples a = new OrganizationSamples();
        return a.buildWired(tables);
    }

}
