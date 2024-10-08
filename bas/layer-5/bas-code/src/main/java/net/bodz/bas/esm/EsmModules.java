package net.bodz.bas.esm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.io.Stdio;

public class EsmModules {

    public static List<IEsmModuleProvider> providers = new ArrayList<>();
    public static Set<EsmModule> modules = new HashSet<>();
    public static DomainMap<EsmModule> registry = new DomainMap<>();

    static {
        for (IEsmModuleProvider provider : ServiceLoader.load(IEsmModuleProvider.class)) {
            providers.add(provider);

            for (EsmModule module : provider.getModules()) {
                modules.add(module);

                for (String pattern : module.getExclusiveDomains()) {
                    EsmModule prev = registry.patternGet(pattern);
                    if (prev != null)
                        throw new DuplicatedKeyException(pattern, prev);
                    registry.patternPut(pattern, module);
                }
            }
        }

        registry.dump(Stdio.out.indented());
    }

}
