package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 存放区域
 */
@IdType(Integer.class)
@Table(name = "region")
public class Region
        extends CoNode<Region, Integer> {

    private static final long serialVersionUID = 1L;

    public static final int ID_AbstractRoot = 1;
    public static final int ID_TemplateRoot = 2;

    Artifact asArtifact;
    ArtifactCategory forArtifactCategory;
    Artifact forArtifact;
    RegionCategory category;
    RegionTag tag;

    public Artifact getAsArtifact() {
        return asArtifact;
    }

    public void setAsArtifact(Artifact asArtifact) {
        this.asArtifact = asArtifact;
    }

    public ArtifactCategory getForArtifactCategory() {
        return forArtifactCategory;
    }

    public void setForArtifactCategory(ArtifactCategory forArtifactCategory) {
        this.forArtifactCategory = forArtifactCategory;
    }

    public Artifact getForArtifact() {
        return forArtifact;
    }

    public void setForArtifact(Artifact forArtifact) {
        this.forArtifact = forArtifact;
    }

    public RegionCategory getCategory() {
        return category;
    }

    public void setCategory(RegionCategory category) {
        this.category = category;
    }

    public RegionTag getTag() {
        return tag;
    }

    public void setTag(RegionTag tag) {
        this.tag = tag;
    }

}
