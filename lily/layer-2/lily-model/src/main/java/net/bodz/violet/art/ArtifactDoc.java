package net.bodz.violet.art;

import javax.persistence.Table;

import net.bodz.lily.template.DocRecord;

@Table(name = "art_doc")
public class ArtifactDoc
        extends DocRecord {

    private static final long serialVersionUID = 1L;

    Artifact artifact;

    public ArtifactDoc() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

}
