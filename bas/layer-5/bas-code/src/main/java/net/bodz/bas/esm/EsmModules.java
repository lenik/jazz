package net.bodz.bas.esm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import net.bodz.bas.err.DuplicatedKeyException;

public class EsmModules {

    public static List<IEsmModuleProvider> providers = new ArrayList<>();
    public static Set<EsmModule> modules = new HashSet<>();
    public static Map<String, EsmModule> packageMap = new HashMap<>();

    static {
        for (IEsmModuleProvider provider : ServiceLoader.load(IEsmModuleProvider.class)) {
            providers.add(provider);

            for (EsmModule module : provider.getModules()) {
                modules.add(module);

                for (String packageName : module.getExclusivePackageNames()) {
                    EsmModule prev = packageMap.get(packageName);
                    if (prev != null)
                        throw new DuplicatedKeyException(packageName, prev);
                    packageMap.put(packageName, module);
                }
            }
        }
    }

}
