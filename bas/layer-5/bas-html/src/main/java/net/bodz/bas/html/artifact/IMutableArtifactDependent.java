package net.bodz.bas.html.artifact;

import net.bodz.bas.err.ConflictedVersionException;

public interface IMutableArtifactDependent
        extends IArtifactDependent {

    MutableArtifactDependency addDependency(String name);

    MutableArtifactDependency addDependency(String name, ArtifactType type);

    MutableArtifactDependency addDependency(String name, ArtifactType type, String minVersionStr, String maxVersionStr)
            throws ConflictedVersionException;

    MutableArtifactDependency addDependency(IArtifact artifact);

    void addDependency(IArtifactDependency dependency)
            throws ConflictedVersionException;

    void removeDependency(String name);

}
