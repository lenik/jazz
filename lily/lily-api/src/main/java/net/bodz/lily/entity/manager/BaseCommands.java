package net.bodz.lily.entity.manager;

public class BaseCommands
        extends AbstractEntityCommandProvider {

    public BaseCommands() {
        addCommand(new CountCommand());
        addCommand(new ListCommand());
        addCommand(new FetchCommand());

        addCommand(new GetDefaultCommand());
        addCommand(new SaveCommand());
        addCommand(new SaveContentCommand());

        addCommand(new DeleteCommand());

        addCommand(new UploadCommand());
        addCommand(new AttachmentResolveCommand());
    }

}
