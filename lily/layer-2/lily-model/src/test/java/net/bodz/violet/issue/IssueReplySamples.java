package net.bodz.violet.issue;

import net.bodz.lily.test.TestSamples;

public class IssueReplySamples
        extends TestSamples {

    public static IssueReply build(Issue issue) {
        IssueReply a = new IssueReply();
        a.setSubject("issueReply-1");
        a.setText("A issueReply named issueReply-1.");
        a.setIssue(issue);
        return a;
    }

}
