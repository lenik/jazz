package net.bodz.violet.manu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

@Table(name = "manucons")
@IdType(Integer.class)
public class ManuConsumable
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    // Asset asset;
    // SalesOrderItem salesOrderItem;
    Artifact artifact;

    public ManuConsumable() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

}
