package net.bodz.lily.entity.manager;

import net.bodz.bas.fmt.json.IJsonForm;

@ForEntityType(IJsonForm.class)
public class SaveCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "save";
    public static final String[] NAMES = { NAME, "saveNew", "update" };

    public SaveCommand() {
    }

    @Override
    public String getPreferredName() {
        return NAME;
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public IEntityCommandProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new SaveProcess(this, context, resolvedEntity);
    }

}
