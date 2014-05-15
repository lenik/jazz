package net.bodz.bas.html.artifact;

import net.bodz.bas.html.ConflictedVersionException;

public interface IMutableArtifactDependent
        extends IArtifactDependent {

    MutableArtifactDependency addDependency(String name, String type);

    MutableArtifactDependency addDependency(String name, String type, String minVersionStr, String maxVersionStr)
            throws ConflictedVersionException;

    void addDependency(IArtifactDependency dependency)
            throws ConflictedVersionException;

    void removeDependency(String name);

}
