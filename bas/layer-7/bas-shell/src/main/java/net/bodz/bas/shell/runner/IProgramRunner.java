package net.bodz.bas.shell.runner;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

/**
 * Class runner.
 */
@IndexedType
public interface IProgramRunner
        extends IPriority {

    /**
     * The base program type which can be handled by this runner.
     * 
     * @return The base program type, non-<code>null</code>.
     */
    Class<?> getProgramType();

    /**
     * Whether this runner is static-only.
     * 
     * @return <code>true</code> if this program runner can only run static program classes.
     */
    boolean isStaticRunner();

    /**
     * @return Loaded context which is used by {@link #run(Object, String...)}. Returns
     *         <code>null</code> is the specified program class isn't supported by this runner.
     */
    Object load(Class<?> programClass);

    /**
     * @return Loaded context which is used by {@link #run(Object, String...)}. Returns
     *         <code>null</code> is the specified program instance isn't supported by this runner.
     */
    Object load(Object instance);

    void run(Object context, String... args)
            throws Exception;

    /**
     * Get the exit status of last run.
     * 
     * @return Last exit status.
     */
    int getExitStatus();

}
