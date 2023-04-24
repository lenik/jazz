package net.bodz.bas.site.vhost.registry;

import net.bodz.bas.err.DuplicatedKeyException;

public interface IDomainRegistry<T> {

    boolean containsDomain(String domain);

    T findDomain(String domain);

    void setDefault(T obj);

    void setDomain(String domain, T obj);

    void setDomainSuffix(String suffix, T obj);

    boolean addDefault(T obj);

    boolean addDomain(String domain, T obj);

    boolean addDomainSuffix(String suffix, T obj);

    default void registerDefault(T obj)
            throws DuplicatedKeyException {
        if (!addDefault(obj))
            throw new DuplicatedKeyException("<default>");
    }

    default void registerDomain(String domain, T obj)
            throws DuplicatedKeyException {
        if (!addDomain(domain, obj))
            throw new DuplicatedKeyException(domain);
    }

    default void registerDomainSuffix(String suffix, T obj)
            throws DuplicatedKeyException {
        if (!addDomainSuffix(suffix, obj))
            throw new DuplicatedKeyException(suffix);
    }

    T removeDomain(String domain);

    T removeDomainSuffix(String prefix);

}
