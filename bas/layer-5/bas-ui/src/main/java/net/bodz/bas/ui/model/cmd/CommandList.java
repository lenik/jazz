package net.bodz.bas.ui.model.cmd;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandList
        implements ICommandProvider {

    private List<ICommand> commands;

    public CommandList() {
        commands = new ArrayList<>();
    }

    @Override
    public Class<?> getTargetClass() {
        return void.class;
    }

    @Override
    public List<ICommand> getCommands(Object o) {
        return commands;
    }

    public void addCommand(ICommand command) {
        if (command == null)
            throw new NullPointerException("command");
        commands.add(command);
    }

    public void removeCommand(ICommand command) {
        commands.remove(command);
    }

}
