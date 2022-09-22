package net.bodz.bas.c.type;

import java.util.function.Function;

@FunctionalInterface
public interface IClassFunction
        extends
            Function<Class<?>, Class<?>> {

    /**
     * @param type
     *            should not be <code>null</code>.
     * @return <code>null</code> if not found.
     */
    @Override
    Class<?> apply(Class<?> type);

}
