package net.bodz.violet.schema.art;

import javax.persistence.Table;

import net.bodz.lily.concrete.util.IValidControl;

@Table(schema = ArtifactModel.SCHEMA_NAME, name = ArtifactModel.TABLE_NAME)
public class ArtifactModel
        extends _ArtifactModel_stuff
        implements
            IValidControl {

    private static final long serialVersionUID = 1L;

    public static final int N_MODEL = 40;

    public interface Props {
        String cost = "cost";
        String priceStrategy = "priceStrategy";
    }

    String modelName;

    final FabCost cost = new FabCost();

    public ArtifactModel() {
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public IValidControl getValidControl() {
        return this;
    }

    public void setValidControl(IValidControl validControl) {
        setValid(validControl.isValid());
        setValidSince(validControl.getValidSince());
        setValidUntil(validControl.getValidUntil());
    }

    public FabCost getCost() {
        return cost;
    }

}
