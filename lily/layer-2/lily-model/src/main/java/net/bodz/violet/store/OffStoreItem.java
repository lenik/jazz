package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;
import net.bodz.violet.art.Artifact;

/**
 * Sum of store without region.
 */
@Table(name = "offstorel")
@IdType(Integer.class)
public class OffStoreItem
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    // String batch;
    double quantity;

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
