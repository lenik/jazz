package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

@IndexedTypeLoader(IActionProvider.class)
public class ActionProviderIndex {

    public static TypePoMap<List<IActionProvider>> clsActionProviders;
    public static List<IActionProvider> generalActionProviders;

    static {
        clsActionProviders = new TypePoMap<List<IActionProvider>>();
        generalActionProviders = new ArrayList<IActionProvider>();

        for (IActionProvider provider : ServiceLoader.load(IActionProvider.class)) {
            Class<?> targetClass = provider.getTargetClass();
            if (targetClass == null) {
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
