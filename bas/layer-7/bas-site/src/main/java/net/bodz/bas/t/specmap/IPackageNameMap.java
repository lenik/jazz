package net.bodz.bas.t.specmap;

import java.util.Set;

public interface IPackageNameMap<val_t> {

    String findPackageNameFor(String key);

    default boolean containsPackageFor(String key) {
        String name = findPackageNameFor(key);
        return name != null;
    }

    Set<String> packageNames();

    boolean containsPackage(String name);

    val_t getPackage(String name);

    val_t putPackage(String name, val_t val);

    default boolean addPackage(String name, val_t val) {
        if (containsPackage(name))
            return false;
        putPackage(name, val);
        return true;
    }

    default val_t getOrAddPackage(String name, val_t initial) {
        if (addPackage(name, initial))
            return initial;
        else
            return getPackage(name);
    }

    val_t removePackage(String name);

    void removeAllPackages();

}
