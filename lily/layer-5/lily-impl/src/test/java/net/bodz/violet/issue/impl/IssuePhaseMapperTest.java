package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssuePhaseSamples;

public class IssuePhaseMapperTest
        extends AbstractTableTest<IssuePhase, IssuePhaseMask, IssuePhaseMapper> {

    @Override
    public IssuePhase buildSample() {
        return IssuePhaseSamples.build();
    }

}
