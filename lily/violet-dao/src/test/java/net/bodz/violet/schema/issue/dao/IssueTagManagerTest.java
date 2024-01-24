package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.issue.IssueTag;
import net.bodz.violet.schema.issue.IssueTagSamples;

public class IssueTagManagerTest
        extends AbstractManagerTest<IssueTag, IssueTagMapper, IssueTagManager> {

    @Override
    public IssueTag buildSample()
            throws Exception {
        IssueTagSamples a = new IssueTagSamples();
        return a.buildWired(tables);
    }

}
