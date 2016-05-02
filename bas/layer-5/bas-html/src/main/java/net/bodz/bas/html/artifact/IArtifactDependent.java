package net.bodz.bas.html.artifact;

import java.util.Collection;

public interface IArtifactDependent {

    Collection<? extends IArtifactDependency> getDependencies();

    Collection<? extends IArtifactDependency> getDependencies(ArtifactType type);

    void accept(IArtifactVisitor visitor);

}
