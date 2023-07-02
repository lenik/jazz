package net.bodz.lily.entity.manager;

public class BaseCommands
        extends AbstractEntityCommandProvider {

    public BaseCommands() {
        addCommand(new CountCommand());
        addCommand(new ListCommand());
        addCommand(new ResolveCommand());

        addCommand(new CreateCommand());
        addCommand(new SaveCommand());

        addCommand(new DeleteCommand());

        addCommand(new UploadCommand());
        addCommand(new AttachmentResolveCommand());
    }

}
