package net.bodz.violet.issue;

import net.bodz.lily.test.TestSamples;

public class IssueCategorySamples
        extends TestSamples {

    public static IssueCategory build() {
        IssueCategory a = new IssueCategory();
        a.setLabel("issueCategory-1");
        a.setDescription("A issueCategory named issueCategory-1.");
        return a;
    }

}
