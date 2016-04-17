package net.bodz.bas.ui.model.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.TypePoMap;

public class CommandIndex {

    TypePoMap<List<ICommand>> clsCommands;
    List<ICommand> generalCommands;

    public CommandIndex() {
        clsCommands = new TypePoMap<>();
        generalCommands = new ArrayList<>();

        load();
    }

    void load() {
        for (ICommand cmd : ServiceLoader.load(ICommand.class)) {
            Class<?> targetClass = cmd.getTargetClass();
            if (targetClass == void.class) {
                generalCommands.add(cmd);
                continue;
            }

            List<ICommand> list = clsCommands.get(targetClass);
            if (list == null)
                clsCommands.put(targetClass, list = new ArrayList<ICommand>());

            list.add(cmd);
        }
    }

}
