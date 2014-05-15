package net.bodz.bas.html.artifact;

import net.bodz.bas.meta.build.VersionRange;
import net.bodz.bas.t.order.IPriority;

public interface IArtifactDependency
        extends IPriority, Comparable<IArtifactDependency> {

    int HIGH = -100;
    int NORMAL = 0;
    int LOW = 100;

    String SCRIPT = "script";
    String STYLESHEET = "stylesheet";

    String getName();

    String getType();

    VersionRange getVersionRange();

    boolean isOptional();

}
