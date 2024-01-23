package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssuePhaseSamples;

public class IssuePhaseMapperTest
        extends AbstractTableTest<IssuePhase, IssuePhaseMapper> {

    @Override
    public IssuePhase buildSample()
            throws Exception {
        IssuePhaseSamples a = new IssuePhaseSamples();
        return a.buildWired(tables);
    }

}
