package net.bodz.lily.entity.manager.cmd;

import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IEntityCommandProcess;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

public class ImportCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_IMPORT;
    public static final String[] NAMES = { "import", "importCsv", "importExcel" };

    public ImportCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public IEntityCommandProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new ImportProcess(this, context);
    }

}
