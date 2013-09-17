package net.bodz.pkg.obfuz.pm;

import net.bodz.bas.t.preorder.PrefixMap;

public class ProtectionModels {

    private static PrefixMap<IProtectionModel> registry;

    public static String floor(String name) {
        if (registry == null)
            return null;
        String k = registry.floorKey(name);
        return k;
    }

    public static IProtectionModel get(String name) {
        if (registry == null)
            return null;
        return registry.get(name);
    }

    public static void register(String name, IProtectionModel model) {
        if (registry == null)
            registry = new PrefixMap<IProtectionModel>();
        registry.put(name, model);
    }

    public static void quit() {
        registry = null;
    }

}
