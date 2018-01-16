package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

@Table(name = "storel")
@IdType(Integer.class)
public class StoreItem
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    Region region;
    // String batch;
    double quantity;

    public StoreItem() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
