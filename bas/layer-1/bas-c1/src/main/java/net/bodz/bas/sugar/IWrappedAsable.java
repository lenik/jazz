package net.bodz.bas.sugar;

public interface IWrappedAsable<base_t> {

    <T extends base_t> T as(Class<T> decoratedType);

}
