package net.bodz.bas.t.order;

import net.bodz.bas.meta.decl.Priority;

public interface IAutoPriority
        extends
            IPriority {

    @Override
    default int getPriority() {
        Priority aPriority = getClass().getAnnotation(Priority.class);
        if (aPriority != null)
            return aPriority.value();
        else
            return 0;
    }

}
