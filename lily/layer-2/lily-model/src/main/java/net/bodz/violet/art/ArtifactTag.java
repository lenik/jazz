package net.bodz.violet.art;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

/**
 * 物品标签
 */
@Table(name = "arttag")
public class ArtifactTag
        extends CoTag<ArtifactTag> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

}
