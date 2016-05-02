package net.bodz.bas.html.artifact;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.build.IVersion;

public abstract class StaticArtifactProvider
        extends AbstractArtifactProvider {

    private IArtifactProvider provider;

    public StaticArtifactProvider(IArtifactProvider provider) {
        this.provider = provider;
    }

    @Override
    public Set<String> getArtifactNames() {
        return provider.getArtifactNames();
    }

    @Override
    public Collection<Map<IVersion, IArtifact>> getArtifacts() {
        return provider.getArtifacts();
    }

    @Override
    public Map<IVersion, IArtifact> getArtifactVersions(String name) {
        return provider.getArtifactVersions(name);
    }

    @Override
    public IArtifact getArtifact(IArtifactDependency dependency) {
        return provider.getArtifact(dependency);
    }

}
