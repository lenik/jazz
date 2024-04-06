package net.bodz.lily.entity.manager.cmd;

import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IHaveAttachments.class)
public class GetIncomingFileCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_GET_INCOMING_FILE;
    public static final String[] NAMES = { "incoming" };

    public GetIncomingFileCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public GetIncomingFileProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new GetIncomingFileProcess(this, context);
    }

}
