package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.TypePoMap;

public class ActionProviderIndex {

    public static TypePoMap<List<IActionProvider>> clsActionProviders;
    public static List<IActionProvider> generalActionProviders;

    static {
        clsActionProviders = new TypePoMap<>();
        generalActionProviders = new ArrayList<>();

        for (IActionProvider provider : ServiceLoader.load(IActionProvider.class)) {
            Class<?> targetClass = provider.getTargetClass();
            if (targetClass == void.class) {
                generalActionProviders.add(provider);
                continue;
            }

            List<IActionProvider> list = clsActionProviders.get(targetClass);
            if (list == null)
                clsActionProviders.put(targetClass, list = new ArrayList<IActionProvider>());

            list.add(provider);
        }
    }

}
