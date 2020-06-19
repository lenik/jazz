package net.bodz.violet.art;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.t.struct.ValidControl;

@IdType(Integer.class)
public class ArtifactModel
        extends CoMomentInterval<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_MODEL = 40;

    public interface Props {
        String cost = "cost";
        String priceStrategy = "priceStrategy";
    }

    ArtifactModel obsolete;

    Artifact artifact;
    String modelName;

    final ValidControl validControl = new ValidControl();
    final FabCost cost = new FabCost();

    public ArtifactModel() {
    }

    public ArtifactModel getObsolete() {
        return obsolete;
    }

    public void setObsolete(ArtifactModel obsolete) {
        this.obsolete = obsolete;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public ValidControl getValidControl() {
        return validControl;
    }

    public FabCost getCost() {
        return cost;
    }

}
