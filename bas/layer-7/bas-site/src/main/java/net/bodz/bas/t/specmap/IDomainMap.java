package net.bodz.bas.t.specmap;

import java.util.Set;

public interface IDomainMap<val_t> {

    String findDomainFor(String key);

    default boolean containsDomainFor(String key) {
        String domain = findDomainFor(key);
        return domain != null;
    }

    Set<String> domainKeySet();

    boolean containsDomain(String domain);

    val_t getDomain(String domain);

    val_t putDomain(String domain, val_t val);

    default boolean addDomain(String domain, val_t val) {
        if (containsDomain(domain))
            return false;
        putDomain(domain, val);
        return true;
    }

    default val_t getOrAddDomain(String domain, val_t initial) {
        if (addDomain(domain, initial))
            return initial;
        else
            return getDomain(domain);
    }

    val_t removeDomain(String domain);

    void removeAllDomains();

}
