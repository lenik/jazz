package net.bodz.bas.html.artifact;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.VersionRange;

public abstract class AbstractArtifactManager
        implements IArtifactManager {

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
    public Set<IArtifact> getClosure(IArtifactDependent dependent, String type, Boolean optional) {
        return new Scanner(type, optional).scan(dependent);
    }

    class Scanner {

        String type;
        Boolean optional;
        Set<IArtifact> closure = new LinkedHashSet<>();

        public Scanner(String type, Boolean optional) {
            this.type = type;
            this.optional = optional;
        }

        public Set<IArtifact> scan(IArtifactDependent dependent) {
            for (IArtifactDependency dependency : dependent.getDependencies()) {
                if (optional != null && optional.booleanValue() != dependency.isOptional())
                    continue;

                if (type != null && !type.equals(dependency.getType()))
                    continue;

                IArtifact artifact = getArtifact(dependency);
                if (artifact == null)
                    throw new NoSuchElementException(dependency.toString());

                if (closure.add(artifact))
                    scan(artifact);
            }
            return closure;
        }

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
