package net.bodz.lily.schema.inet.dao;

import net.bodz.lily.schema.inet.ExternalSite;
import net.bodz.lily.schema.inet.ExternalSiteSamples;
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
