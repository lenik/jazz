package net.bodz.violet.store;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public class ArtifactPrice
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    double buyPrice;
    double sellPrice;

    public ArtifactPrice() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    /**
     * The price which should be appeared in the purchase order.
     * 
     * Generally, the artifact is purchased instead of manufactured.
     * 
     * For customer, it's the reclaim price.
     */
    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * The price which should be appeared in the sales order.
     */
    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

}
