package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.issue.IssuePhase;
import net.bodz.violet.schema.issue.IssuePhaseSamples;

public class IssuePhaseManagerTest
        extends AbstractManagerTest<IssuePhase, IssuePhaseMapper, IssuePhaseManager> {

    @Override
    public IssuePhase buildSample()
            throws Exception {
        IssuePhaseSamples a = new IssuePhaseSamples();
        return a.buildWired(tables);
    }

}
