package net.bodz.bas.html.artifact;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IArtifactProvider {

    Set<String> getArtifactNames();

    /**
     * (version -> artifact)*
     */
    Collection<Map<IVersion, IArtifact>> getArtifacts();

    /**
     * version -> artifact
     *
     * @return <code>null</code> if the artifact name isn't available.
     */
    Map<IVersion, IArtifact> getArtifactVersions(String name);

    /**
     * @return <code>null</code> if the dependency couldn't resolved.
     */
    IArtifact getArtifact(IArtifactDependency dependency);

}
