package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.issue.IssueReply;
import net.bodz.violet.issue.IssueReplySamples;

public class IssueReplyMapperTest
        extends AbstractMapperTest<IssueReply, IssueReplyMask, IssueReplyMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public IssueReply buildSample() {
        return IssueReplySamples.build();
    }

}
