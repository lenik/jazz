package net.bodz.violet.issue;

import net.bodz.lily.test.TestSamples;

public class IssueLogSamples
        extends TestSamples {

    public static IssueLog build(Issue issue) {
        IssueLog a = new IssueLog();
        a.setSubject("issueReply-1");
        a.setRawText("A issueReply named issueReply-1.");
        a.setIssue(issue);
        return a;
    }

}
