package net.bodz.lily.entity.manager.cmd;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandProcess;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IJsonForm.class)
public class SaveCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_SAVE;

    // save = update | saveNew
    public static final String[] NAMES = { "save", "update", "saveNew", };

    // PUT for save | update, PATCH for update, POST for save | saveNew
    public static final String[] METHODS = { "PUT", "PATCH", "POST" };

    public SaveCommand() {
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
    public IEntityCommandProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new SaveProcess(this, context, resolvedEntity);
    }

}
