package net.bodz.violet.art;

import net.bodz.lily.template.VoteRecord;

public class ArtifactVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    Artifact artifact;

    public ArtifactVote() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

}
