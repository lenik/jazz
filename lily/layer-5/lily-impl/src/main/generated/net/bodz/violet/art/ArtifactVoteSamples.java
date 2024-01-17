package net.bodz.violet.art;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.dao.ArtifactMapper;

public class ArtifactVoteSamples
        extends TestSampleBuilder {

    public User user;
    public Artifact parent;

    @Override
    public ArtifactVote build()
            throws Exception {
        ArtifactVote a = new ArtifactVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(1164679130);
        return a;
    }

    @Override
    public ArtifactVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public ArtifactVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
