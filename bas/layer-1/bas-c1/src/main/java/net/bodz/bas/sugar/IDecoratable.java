package net.bodz.bas.sugar;

public interface IDecoratable<decorator_base_t> {

    <T extends decorator_base_t> T decorate(Class<T> decoratedType);

}
