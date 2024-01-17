package net.bodz.lily.inet.dao;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.inet.ExternalSiteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ExternalSiteMapperTest
        extends AbstractTableTest<ExternalSite, ExternalSiteMapper> {

    @Override
    public ExternalSite buildSample()
            throws Exception {
        ExternalSiteSamples a = new ExternalSiteSamples();
        return a.buildWired(tables);
    }

}
