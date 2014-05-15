package net.bodz.bas.html.artifact;

import net.bodz.bas.meta.build.VersionRange;

public abstract class AbstractArtifactDependency
        implements IArtifactDependency {

    @Override
    public int compareTo(IArtifactDependency o) {
        if (o == null)
            return 1;

        String name1 = getName();
        String name2 = o.getName();
        int cmp = name1.compareTo(name2);
        if (cmp != 0)
            return cmp;

        VersionRange range1 = getVersionRange();
        VersionRange range2 = o.getVersionRange();
        cmp = range1.compareTo(range2);
        if (cmp != 0)
            return cmp;

        return equals(o) ? 0 : -1;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getType());
        buf.append(":");
        buf.append(getName());
        buf.append(":");
        buf.append(getVersionRange());
        if (isOptional())
            buf.append(" (optional)");
        return buf.toString();
    }

}
