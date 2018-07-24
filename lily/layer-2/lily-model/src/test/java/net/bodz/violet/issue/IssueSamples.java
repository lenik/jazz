package net.bodz.violet.issue;

public class IssueSamples {

    public static Issue build() {
        Issue a = new Issue();
        a.setLabel("issue-1");
        a.setDescription("A issue named issue-1.");
        return a;
    }

}
