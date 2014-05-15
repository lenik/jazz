package net.bodz.bas.html.artifact;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class MutableArtifactManager
        extends AbstractArtifactManager
        implements IArtifactManager {

    protected Map<String, Map<IVersion, IArtifact>> nameMap;

    public MutableArtifactManager() {
        nameMap = new HashMap<>();
    }

    @Override
    public Set<String> getArtifactNames() {
        return nameMap.keySet();
    }

    @Override
    public Collection<Map<IVersion, IArtifact>> getArtifacts() {
        return nameMap.values();
    }

    @Override
    public Map<IVersion, IArtifact> getArtifactVersions(String name) {
        return nameMap.get(name);
    }

    @Override
    public void addArtifact(IArtifact artifact) {
        if (artifact == null)
            throw new NullPointerException("artifact");
        String name = artifact.getName();

        Map<IVersion, IArtifact> versionMap = nameMap.get(name);
        if (versionMap == null)
            synchronized (nameMap) {
                if ((versionMap = nameMap.get(name)) == null) {
                    versionMap = new HashMap<>();
                    nameMap.put(name, versionMap);
                }
            }

        versionMap.put(artifact.getVersion(), artifact);
    }

    @Override
    public void removeArtifact(String name) {
        nameMap.remove(name);
    }

    @Override
    public void removeArtifact(String name, IVersion version) {
        Map<IVersion, IArtifact> versions = nameMap.get(name);
        if (versions != null) {
            versions.remove(version);
            if (versions.isEmpty())
                removeArtifact(name);
        }
    }

    @Override
    public void removeArtifact(IArtifact artifact) {
        removeArtifact(artifact.getName(), artifact.getVersion());
    }

}
