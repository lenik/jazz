package net.bodz.lily.entity.manager;

import net.bodz.bas.fmt.json.IJsonForm;

@ForEntityType(IJsonForm.class)
public class ListCommand
        extends AbstractEntityCommandType {

    public static final String NAME = "__data";
    public static final String[] NAMES = { NAME, "list" };

    public ListCommand() {
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
        return new ListProcess(this, context);
    }

}