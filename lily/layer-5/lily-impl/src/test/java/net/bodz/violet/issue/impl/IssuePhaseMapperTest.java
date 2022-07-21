package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.issue.IssuePhase;
import net.bodz.violet.issue.IssuePhaseSamples;

public class IssuePhaseMapperTest
        extends AbstractMapperTest<IssuePhase, IssuePhaseMask, IssuePhaseMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public IssuePhase buildSample() {
        return IssuePhaseSamples.build();
    }

}
