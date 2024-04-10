package net.bodz.lily.entity.manager.cmd;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.entity.manager.AbstractEntityCommandType;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.entity.manager.IEntityCommandContext;
import net.bodz.lily.entity.manager.IStdCommands;
import net.bodz.lily.entity.manager.ResolvedEntity;

@ForEntityType(IHaveAttachments.class)
public class AttachmentResolveCommand
        extends AbstractEntityCommandType {

    static final Logger logger = LoggerFactory.getLogger(AttachmentResolveCommand.class);

    public static final String ID = IStdCommands.ID_ATTACHMENT_RESOLVE;
    public static final String[] NAMES = { "attachment", "file" };

    public AttachmentResolveCommand() {
        super(ID);
    }

    @Override
    public String[] getCommandNames() {
        return NAMES;
    }

    @Override
    public boolean isContentCommand() {
        return true;
    }

    @Override
    public AttachmentResolveProcess defaultCreateProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity) {
        return new AttachmentResolveProcess(this, context, resolvedEntity);
    }
}
