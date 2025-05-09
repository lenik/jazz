package net.bodz.bas.make;

import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

public abstract class BasicMakeRule<T extends IMakeTarget>
        implements IMakeRule<T> {

    IMakefile makefile;
    int priority = 0;

    T target;
    Set<IMakeTarget> sources = new LinkedHashSet<>();

    public BasicMakeRule(@NotNull IMakefile makefile, @NotNull T target) {
        this.makefile = makefile;
        this.target = target;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @NotNull
    @Override
    public IMakefile getMakefile() {
        return makefile;
    }

    @NotNull
    @Override
    public Set<IMakeTarget> getSources() {
        return sources;
    }

    @Override
    public T getTarget() {
        return target;
    }

}
