package net.bodz.bas.ui.model.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.TypePoMap;

public class CommandProviderIndex {

    TypePoMap<List<ICommandProvider>> clsCommandProviders;
    List<ICommandProvider> commandProviders;

    public CommandProviderIndex() {
        clsCommandProviders = new TypePoMap<>();
        commandProviders = new ArrayList<>();

        load();
    }

    void load() {
        for (ICommandProvider provider : ServiceLoader.load(ICommandProvider.class)) {
            Class<?> targetClass = provider.getTargetClass();
            if (targetClass == void.class) {
                commandProviders.add(provider);
                continue;
            }

            List<ICommandProvider> list = clsCommandProviders.get(targetClass);
            if (list == null)
                clsCommandProviders.put(targetClass, list = new ArrayList<ICommandProvider>());

            list.add(provider);
        }
    }

}
