package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.IssueTag;
import net.bodz.violet.issue.IssueTagSamples;

public class IssueTagManagerTest
        extends AbstractManagerTest<IssueTag, IssueTagMapper, IssueTagManager> {

    @Override
    public IssueTag buildSample()
            throws Exception {
        IssueTagSamples a = new IssueTagSamples();
        return a.buildWired(tables);
    }

}
