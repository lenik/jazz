package net.bodz.violet.art;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArtifactVoteSamples
        extends TestSampleBuilder {

    public User user;
    public Artifact parent;

    public ArtifactVote build()
            throws Exception {
        ArtifactVote a = new ArtifactVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(1164679130);
        return a;
    }

}
