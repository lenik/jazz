package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.PartyCategory;
import net.bodz.lily.contact.PartyCategorySamples;
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
