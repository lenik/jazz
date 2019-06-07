package net.bodz.violet.issue;

import net.bodz.lily.test.TestSamples;

public class IssuePhaseSamples
        extends TestSamples {

    public static IssuePhase build() {
        IssuePhase a = new IssuePhase();
        a.setLabel("issuePhase-1");
        a.setDescription("A issuePhase named issuePhase-1.");
        return a;
    }

}
