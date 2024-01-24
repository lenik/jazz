package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.IssuePhase;
import net.bodz.violet.schema.issue.IssuePhaseSamples;

public class IssuePhaseMapperTest
        extends AbstractTableTest<IssuePhase, IssuePhaseMapper> {

    @Override
    public IssuePhase buildSample()
            throws Exception {
        IssuePhaseSamples a = new IssuePhaseSamples();
        return a.buildWired(tables);
    }

}
