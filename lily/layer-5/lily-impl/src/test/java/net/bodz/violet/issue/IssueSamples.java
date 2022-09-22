package net.bodz.violet.issue;

import net.bodz.lily.test.TestSamples;

public class IssueSamples
        extends TestSamples {

    public static Issue build(IssueCategory category, IssuePhase phase) {
        Issue a = new Issue();
        a.setSubject("issue-1");
        a.setRawText("A issue named issue-1.");
        a.setCategory(category);
        a.setPhase(phase);
        return a;
    }

}
