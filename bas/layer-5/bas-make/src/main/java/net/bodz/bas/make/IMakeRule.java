package net.bodz.bas.make;

import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.order.IPriority;

public interface IMakeRule<T extends IMakeTarget>
        extends IPriority,
                IMakeable<T> {

    @NotNull
    IMakefile getMakefile();

    @NotNull
    Set<IMakeTarget> getSources();

    @Override
    default boolean isExpired() {
        IMakefile makefile = getMakefile();
        long targetVersion = getTarget().getVersion();
next_source:
        for (IMakeTarget source : getSources()) {
            long sourceVersion = source.getVersion();
            if (sourceVersion > targetVersion)
                return true;
            for (IMakeRule<IMakeTarget> sourceRule : makefile.getRules(source))
                if (sourceRule.isUpdated())
                    continue next_source;
            // none of source rules is updated.
            return true;
        }
        return false;
    }

}
