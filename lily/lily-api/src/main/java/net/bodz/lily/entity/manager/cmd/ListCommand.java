package net.bodz.lily.entity.manager.cmd;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandProcess;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IJsonForm.class)
public class ListCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_LIST;
    public static final String[] NAMES = { "__data*", "list" };

    public ListCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public IEntityCommandProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new ListProcess(this, context);
    }

}