package net.bodz.lily.entity.manager.cmd;

import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IId.class)
public class DeleteCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_DELETE;
    public static final String[] NAMES = { "delete", "del", "rm" };
    public static final String[] METHODS = { "DELETE" };

    public DeleteCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public String[] getAcceptedMethods() {
        return METHODS;
    }

    @Override
    public DeleteProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new DeleteProcess(this, context);
    }

}
