package net.bodz.violet.fab;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;

@Table(name = "fabcons")
@IdType(Integer.class)
public class FabConsumable
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    // Asset asset;
    // SalesOrderItem salesOrderItem;
    Artifact artifact;

    public FabConsumable() {
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

}
