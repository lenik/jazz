package net.bodz.lily.entity.manager;

import net.bodz.lily.entity.manager.cmd.AttachmentResolveCommand;
import net.bodz.lily.entity.manager.cmd.ContentDeleteCommand;
import net.bodz.lily.entity.manager.cmd.ContentSaveCommand;
import net.bodz.lily.entity.manager.cmd.CountCommand;
import net.bodz.lily.entity.manager.cmd.DeleteCommand;
import net.bodz.lily.entity.manager.cmd.FetchCommand;
import net.bodz.lily.entity.manager.cmd.GetDefaultCommand;
import net.bodz.lily.entity.manager.cmd.GetIncomingFileCommand;
import net.bodz.lily.entity.manager.cmd.ImportCommand;
import net.bodz.lily.entity.manager.cmd.ListCommand;
import net.bodz.lily.entity.manager.cmd.SaveCommand;
import net.bodz.lily.entity.manager.cmd.UploadCommand;

public class BaseCommands
        extends AbstractEntityCommandProvider {

    public BaseCommands() {
        addCommand(new FetchCommand());
        addCommand(new ContentSaveCommand()); // save & update
        addCommand(new ContentDeleteCommand());

        addCommand(new CountCommand());
        addCommand(new ListCommand());

        addCommand(new GetDefaultCommand());
        addCommand(new SaveCommand());
        addCommand(new DeleteCommand());

        addCommand(new UploadCommand());
        addCommand(new AttachmentResolveCommand());
        addCommand(new GetIncomingFileCommand());

        addCommand(new ImportCommand());
    }

}
