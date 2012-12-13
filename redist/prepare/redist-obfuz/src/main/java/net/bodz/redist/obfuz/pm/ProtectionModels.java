package net.bodz.redist.obfuz.pm;

import net.bodz.bas.t.preorder.PrefixMap;

public class ProtectionModels {

    private static PrefixMap<ProtectionModel> registry;

    public static String floor(String name) {
        if (registry == null)
            return null;
        String k = registry.floorKey(name);
        return k;
    }

    public static ProtectionModel get(String name) {
        if (registry == null)
            return null;
        return registry.get(name);
    }

    public static void register(String name, ProtectionModel model) {
        if (registry == null)
            registry = new PrefixMap<ProtectionModel>();
        registry.put(name, model);
    }

    public static void quit() {
        registry = null;
    }

}
