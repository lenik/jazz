package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.OrganizationSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class OrganizationManagerTest
        extends AbstractManagerTest<Organization, OrganizationMapper, OrganizationManager> {

    @Override
    public Organization buildSample()
            throws Exception {
        OrganizationSamples a = new OrganizationSamples();
        return a.buildWired(tables);
    }

}
