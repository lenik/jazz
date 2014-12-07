package net.bodz.bas.html.artifact;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.meta.build.VersionRange;
import net.bodz.bas.t.order.IMutablePriority;

public class MutableArtifactDependency
        extends AbstractArtifactDependency
        implements IMutablePriority {

    private int priority;
    private String name;
    private ArtifactType type;
    private VersionRange versionRange;
    private boolean optional = false;

    public MutableArtifactDependency(IArtifactDependency o) {
        if (o == null)
            throw new NullPointerException("o");
        priority = o.getPriority();
        name = o.getName();
        type = o.getType();
        versionRange = o.getVersionRange();
        optional = o.isOptional();
    }

    public MutableArtifactDependency(String name, ArtifactType type, VersionRange versionRange) {
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");
        if (versionRange == null)
            throw new NullPointerException("versionRange");
        this.name = name;
        this.type = type;
        this.versionRange = versionRange;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ArtifactType getType() {
        return type;
    }

    public void setType(ArtifactType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public VersionRange getVersionRange() {
        return versionRange;
    }

    public void setVersionRange(VersionRange versionRange) {
        if (versionRange == null)
            throw new NullPointerException("versionRange");
        this.versionRange = versionRange;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public void restrict(IArtifactDependency other) {
        if (other == null)
            throw new NullPointerException("other");

        if (!Nullables.equals(type, other.getType()))
            throw new IllegalArgumentException("incompatible");
        if (!Nullables.equals(name, other.getName()))
            throw new IllegalArgumentException("incompatible");

        optional &= other.isOptional();
        versionRange = versionRange.intersect(other.getVersionRange());
    }

}
