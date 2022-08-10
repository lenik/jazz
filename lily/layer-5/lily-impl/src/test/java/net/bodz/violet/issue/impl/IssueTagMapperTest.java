package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.issue.IssueTag;
import net.bodz.violet.issue.IssueTagSamples;

public class IssueTagMapperTest
        extends AbstractMapperTest<IssueTag, IssueTagMask, IssueTagMapper> {

    @Override
    public IssueTag buildSample() {
        return IssueTagSamples.build();
    }

}
