package net.bodz.lily.entity.manager.cmd;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IJsonForm.class)
public class GetDefaultCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_GET_DEFAULT;
    public static final String[] NAMES = { "default", "new" };
    public static final String[] METHODS = { "GET" };

    public GetDefaultCommand() {
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
    public CreateProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new CreateProcess(this, context);
    }

}
