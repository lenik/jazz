package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssuePhaseSamples;

public class IssuePhaseManagerTest
        extends AbstractManagerTest<IssuePhase, IssuePhaseMapper, IssuePhaseManager> {

    @Override
    public IssuePhase buildSample()
            throws Exception {
        IssuePhaseSamples a = new IssuePhaseSamples();
        return a.buildWired(tables);
    }

}
