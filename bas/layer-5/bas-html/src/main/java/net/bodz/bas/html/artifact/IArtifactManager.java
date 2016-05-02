package net.bodz.bas.html.artifact;

import java.util.Set;

import net.bodz.bas.meta.build.IVersion;

public interface IArtifactManager
        extends IArtifactProvider {

    String ATTRIBUTE_KEY = IArtifactManager.class.getName();

    void addArtifact(IArtifact artifact);

    void removeArtifact(String name);

    void removeArtifact(String name, IVersion version);

    void removeArtifact(IArtifact artifact);

    Set<IArtifact> getClosure(IArtifactDependent dependent);

    Set<IArtifact> getClosure(IArtifactDependent dependent, ArtifactType type, Boolean optional);

}
