package net.bodz.bas.html.artifact;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.VersionRange;

public abstract class AbstractArtifactManager
        implements IArtifactManager {

    static final Logger logger = LoggerFactory.getLogger(AbstractArtifactManager.class);

    @Override
    public IArtifact getArtifact(IArtifactDependency dependency) {
        String name = dependency.getName();
        VersionRange versionRange = dependency.getVersionRange();

        Map<IVersion, IArtifact> map = getArtifactVersions(name);
        if (map == null)
            return null;

        for (Entry<IVersion, IArtifact> entry : map.entrySet()) {
            IVersion version = entry.getKey();
            IArtifact artifact = entry.getValue();
            if (version == null || versionRange.contains(version))
                return artifact;
        }

        return null;
    }

    @Override
    public Set<IArtifact> getClosure(IArtifactDependent dependent) {
        return getClosure(dependent, null, null);
    }

    @Override
    public Set<IArtifact> getClosure(IArtifactDependent dependent, ArtifactType type, Boolean optional) {
        ArtifactScanner scanner = new ArtifactScanner(this, type, optional);
        dependent.accept(scanner);
        return scanner.getSortedResult();
    }

    @Override
    public String toString() {
        Set<String> names = getArtifactNames();
        StringBuilder buf = new StringBuilder(names.size());
        for (String name : getArtifactNames()) {
            buf.append(name);
            buf.append(", ");
        }
        if (!names.isEmpty())
            buf.setLength(buf.length() - 2);
        return buf.toString();
    }

}
