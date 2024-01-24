package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.PartyCategory;
import net.bodz.lily.schema.contact.PartyCategorySamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PartyCategoryManagerTest
        extends AbstractManagerTest<PartyCategory, PartyCategoryMapper, PartyCategoryManager> {

    @Override
    public PartyCategory buildSample()
            throws Exception {
        PartyCategorySamples a = new PartyCategorySamples();
        return a.buildWired(tables);
    }

}
