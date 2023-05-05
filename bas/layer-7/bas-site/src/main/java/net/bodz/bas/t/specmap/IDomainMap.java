package net.bodz.bas.t.specmap;

import java.util.Set;

public interface IDomainMap<val_t> {

    String findDomainNameFor(String key);

    default boolean containsDomainFor(String key) {
        String domain = findDomainNameFor(key);
        return domain != null;
    }

    Set<String> domainNames();

    boolean containsDomain(String name);

    val_t getDomain(String name);

    val_t putDomain(String name, val_t val);

    default boolean addDomain(String name, val_t val) {
        if (containsDomain(name))
            return false;
        putDomain(name, val);
        return true;
    }

    default val_t getOrAddDomain(String name, val_t initialVal) {
        if (addDomain(name, initialVal))
            return initialVal;
        else
            return getDomain(name);
    }

    val_t removeDomain(String name);

    void removeAllDomains();

}
