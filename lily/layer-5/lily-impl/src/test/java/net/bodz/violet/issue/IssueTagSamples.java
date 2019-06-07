package net.bodz.violet.issue;

import net.bodz.lily.test.TestSamples;

public class IssueTagSamples
        extends TestSamples {

    public static IssueTag build() {
        IssueTag a = new IssueTag();
        a.setLabel("issueTag-1");
        a.setDescription("A issueTag named issueTag-1.");
        return a;
    }

}
