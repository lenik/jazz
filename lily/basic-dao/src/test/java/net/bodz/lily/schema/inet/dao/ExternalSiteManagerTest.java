package net.bodz.lily.schema.inet.dao;

import net.bodz.lily.schema.inet.ExternalSite;
import net.bodz.lily.schema.inet.ExternalSiteSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ExternalSiteManagerTest
        extends AbstractManagerTest<ExternalSite, ExternalSiteMapper, ExternalSiteManager> {

    @Override
    public ExternalSite buildSample()
            throws Exception {
        ExternalSiteSamples a = new ExternalSiteSamples();
        return a.buildWired(tables);
    }

}
