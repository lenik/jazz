package net.bodz.bas.ctx.scope.experimental;

public interface IBeanProvider {

    <T> T get(Class<T> clazz);

}
