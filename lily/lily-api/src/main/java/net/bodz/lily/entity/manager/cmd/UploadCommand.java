package net.bodz.lily.entity.manager.cmd;

import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IHaveAttachments.class)
public class UploadCommand
        extends AbstractEntityCommandType {

    public static final String ID = IStdCommands.ID_UPLOAD;
    public static final String[] NAMES = { ID };
    public static final String[] METHODS = { "POST", "PUT" };

    public static final String K_NULL_ID = "incoming";

    public UploadCommand() {
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
    public boolean isContentCommand() {
        return true;
    }

    @Override
    public UploadProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new UploadProcess(this, context);
    }

}
