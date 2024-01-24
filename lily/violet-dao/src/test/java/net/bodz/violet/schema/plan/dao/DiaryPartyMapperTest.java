package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.plan.DiaryParty;
import net.bodz.violet.schema.plan.DiaryPartySamples;

public class DiaryPartyMapperTest
        extends AbstractTableTest<DiaryParty, DiaryPartyMapper> {

    @Override
    public DiaryParty buildSample()
            throws Exception {
        DiaryPartySamples a = new DiaryPartySamples();
        return a.buildWired(tables);
    }

}
