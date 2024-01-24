package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.OrganizationSamples;
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
