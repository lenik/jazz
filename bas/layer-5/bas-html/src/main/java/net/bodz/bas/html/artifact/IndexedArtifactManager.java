package net.bodz.bas.html.artifact;

import java.util.Map;
import java.util.ServiceLoader;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class IndexedArtifactManager
        extends MutableArtifactManager {

    public IndexedArtifactManager() {
        reload();
    }

    void reload() {
        nameMap.clear();
        for (IArtifactProvider provider : ServiceLoader.load(IArtifactProvider.class))
            for (Map<?, IArtifact> map : provider.getArtifacts())
                for (IArtifact artifact : map.values())
                    addArtifact(artifact);
    }

    static IndexedArtifactManager instance = new IndexedArtifactManager();

    public static IndexedArtifactManager getInstance() {
        return instance;
    }

}
