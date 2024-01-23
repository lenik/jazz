package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.PartyCategory;
import net.bodz.lily.contact.PartyCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class PartyCategoryMapperTest
        extends AbstractTableTest<PartyCategory, PartyCategoryMapper> {

    @Override
    public PartyCategory buildSample()
            throws Exception {
        PartyCategorySamples a = new PartyCategorySamples();
        return a.buildWired(tables);
    }

}
