package net.bodz.bas.html.artifact;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.ConflictedVersionException;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.VersionRange;

public class MutableArtifactDependent
        implements IMutableArtifactDependent, Comparator<String> {

    private Map<String, MutableArtifactDependency> nameMap;
    private Map<ArtifactType, Map<String, MutableArtifactDependency>> typeMap;

    public MutableArtifactDependent() {
        nameMap = new LinkedHashMap<>();
        typeMap = new HashMap<>();
    }

    @Override
    public Collection<? extends IArtifactDependency> getDependencies() {
        return nameMap.values();
    }

    @Override
    public Collection<? extends IArtifactDependency> getDependencies(ArtifactType type) {
        return getDependencyMap(type).values();
    }

    protected Map<String, MutableArtifactDependency> getDependencyMap(ArtifactType type) {
        if (type == null)
            throw new NullPointerException("type");
        Map<String, MutableArtifactDependency> map = typeMap.get(type);
        if (map == null)
            synchronized (typeMap) {
                if ((map = typeMap.get(type)) == null)
                    typeMap.put(type, map = new LinkedHashMap<>());
            }
        return map;
    }

    /** ⇱ Implementation Of {@link IMutableArtifactDependent}. */
    /* _____________________________ */static section.iface __MUTABLE__;

    @Override
    public final MutableArtifactDependency addDependency(String name, ArtifactType type) {
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");

        MutableArtifactDependency dependency = nameMap.get(name);
        if (dependency == null) {
            dependency = new MutableArtifactDependency(name, type, new VersionRange());
            nameMap.put(name, dependency);

            Map<String, MutableArtifactDependency> tNameMap = getDependencyMap(type);
            tNameMap.put(name, dependency);
        }
        return dependency;
    }

    @Override
    public final MutableArtifactDependency addDependency(String name, ArtifactType type, String minVersionStr,
            String maxVersionStr)
            throws ConflictedVersionException {
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");
        VersionRange range = VersionRange.parse(minVersionStr, maxVersionStr);
        addDependency(new MutableArtifactDependency(name, type, range));
        return nameMap.get(name);
    }

    @Override
    public MutableArtifactDependency addDependency(IArtifact artifact) {
        String name = artifact.getName();
        ArtifactType type = artifact.getType();
        IVersion version = artifact.getVersion();
        if (version == null)
            return addDependency(name, type);
        else {
            String versionStr = version.toString();
            return addDependency(name, type, versionStr, versionStr);
        }
    }

    @Override
    public void addDependency(IArtifactDependency dependency)
            throws ConflictedVersionException {
        if (dependency == null)
            throw new NullPointerException("dependency");

        String name = dependency.getName();
        ArtifactType type = dependency.getType();

        MutableArtifactDependency prev = nameMap.get(name);
        if (dependency.equals(prev))
            return;

        MutableArtifactDependency mutable = new MutableArtifactDependency(dependency);
        if (prev != null) {
            mutable.restrict(prev);
            if (mutable.getVersionRange().isEmpty())
                throw new ConflictedVersionException();
        }

        nameMap.put(name, mutable);

        Map<String, MutableArtifactDependency> tNameMap = getDependencyMap(type);
        tNameMap.put(name, mutable);
    }

    @Override
    public void removeDependency(String name) {
        nameMap.remove(name);
        for (Map<String, MutableArtifactDependency> tNameMap : typeMap.values())
            tNameMap.remove(name);
    }

    @Override
    public int compare(String o1, String o2) {
        IArtifactDependency r1 = nameMap.get(o1);
        IArtifactDependency r2 = nameMap.get(o2);
        return Nullables.compare(r1, r2);
    }

}
