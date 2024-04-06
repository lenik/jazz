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
    public static final String[] NAMES = { "save", "saveNew", "update" };

    public SaveCommand() {
        super(ID);
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
