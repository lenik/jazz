package net.bodz.violet.manu;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactModel;

@Table(name = "manustdproc_in")
@IdType(Integer.class)
public class ManuStdProcessInput
        extends CoEntity<Integer> {
    private static final long serialVersionUID = 1L;

    ManuStdProcess process;
    ArtifactModel model;
    Artifact artifact;
    BigDecimal quantity = BigDecimal.ONE;

    public ManuStdProcessInput() {
    }

    public ManuStdProcess getProcess() {
        return process;
    }

    public void setProcess(ManuStdProcess process) {
        this.process = process;
    }

    /**
     * Either model or artifact must be set.
     */
    public ArtifactModel getModel() {
        return model;
    }

    public void setModel(ArtifactModel model) {
        this.model = model;
    }

    /**
     * material without model. (aka. raw material)
     */
    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

}
