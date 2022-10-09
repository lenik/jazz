package net.bodz.lily.entity.manager;

public class BaseCommands
        extends AbstractEntityCommandProvider {

    public BaseCommands() {
        addCommand(CountCommand.builder());
        addCommand(ListCommand.builder());
        addCommand(ResolveCommand.builder());

        addCommand(CreateCommand.builder());
        addCommand(SaveCommand.builder());

        addCommand(DeleteCommand.builder());

        addCommand(UploadCommand.builder());
        addCommand(AttachmentResolver.builder());
    }

}
