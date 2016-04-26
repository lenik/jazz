package net.bodz.bas.html.artifact;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.err.UnsupportedFeatureException;
import net.bodz.bas.http.ctx.IAnchor;
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
        return new Scanner(this, type, optional).listAndSort(dependent);
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

    protected IArtifact javascript(String name, String versionStr, IAnchor anchor) {
        MutableWebArtifact artifact = MutableWebArtifact.javascript(name, versionStr, anchor);
        addArtifact(artifact);
        return artifact;
    }

    protected IArtifact css(String name, String versionStr, IAnchor anchor) {
        MutableWebArtifact artifact = MutableWebArtifact.css(name, versionStr, anchor);
        addArtifact(artifact);
        return artifact;
    }

    protected IArtifact group(String name) {
        MutableWebArtifact artifact = new MutableWebArtifact(name, "x", null, null);
        artifact.setType(ArtifactType.GROUP);
        addArtifact(artifact);
        return artifact;
    }

    protected IArtifact group(String name, IAnchor start, String... fragmentFiles) {
        IArtifact group = group(name);

        IArtifact last = null;
        for (String file : fragmentFiles) {
            if (file.startsWith("#"))
                continue;
            IAnchor a = start.join(file);
            String version = "x";
            String base = FilePath.getBaseName(file);
            String ext = FilePath.getExtension(file, true);
            String fragmentName = name + "/" + base;
            IArtifact fragment;
            switch (ext) {
            case ".js":
                fragment = javascript(fragmentName, version, a);
                break;
            case ".css":
                fragment = css(fragmentName, version, a);
                break;
            default:
                throw new UnsupportedFeatureException("Extension: " + ext);
            }
            if (last != null)
                fragment.addDependency(last);
            last = fragment;
        }
        group.addDependency(last);
        return group;
    }
}

class Scanner {

    static final Logger logger = LoggerFactory.getLogger(Scanner.class);

    static class Item {
        IArtifact artifact;
        int depth;
        int priority;
        int seq;
    }

    static class ItemComparator
            implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            int cmp = o1.depth - o2.depth;
            if (cmp != 0)
                return -cmp; // order by depth descending

            cmp = o1.priority - o2.priority;
            if (cmp != 0)
                return cmp;
            cmp = o1.seq - o2.seq;
            return cmp;
        }

        static final ItemComparator INSTANCE = new ItemComparator();

    }

    IArtifactManager artifactManager;
    ArtifactType type;
    Boolean optional;
    int seq;
    Map<IArtifact, Item> closure = new HashMap<>();

    public Scanner(IArtifactManager artifactManager, ArtifactType type, Boolean optional) {
        this.artifactManager = artifactManager;
        this.type = type;
        this.optional = optional;
    }

    public Set<IArtifact> listAndSort(IArtifactDependent dependent) {
        Stack<IArtifactDependent> stack = new Stack<>();
        scan(dependent, stack);

        List<Item> list = new ArrayList<>(closure.size());
        for (Item item : closure.values())
            if (type == null || type == item.artifact.getType()) {
                list.add(item);
            }

        Collections.sort(list, ItemComparator.INSTANCE);

        LinkedHashSet<IArtifact> artifacts = new LinkedHashSet<>();
        for (Item item : list)
            artifacts.add(item.artifact);
        return artifacts;
    }

    void scan(IArtifactDependent dependent, Stack<IArtifactDependent> stack) {
        int depth = stack.size();
        if (stack.contains(dependent)) {
            logger.error("Detected dependency dead loop. n=" + stack.size());
            for (IArtifactDependent a : stack)
                logger.error("    item: " + a);
            logger.error("    error: " + dependent);
            return;
        }
        stack.push(dependent);

        for (IArtifactDependency dependency : dependent.getDependencies()) {
            if (optional != null && optional.booleanValue() != dependency.isOptional())
                continue;

            int priority = dependency.getPriority();
            IArtifact artifact = artifactManager.getArtifact(dependency);
            if (artifact == null)
                throw new NoSuchElementException(dependency.toString());

            Item item = closure.get(artifact);
            if (item == null) {
                item = new Item();
                item.artifact = artifact;
                item.depth = depth;
                item.priority = priority;
                item.seq = seq++;
                closure.put(artifact, item);
            } else {
                item.depth = Math.max(item.depth, depth);
                item.priority = Math.min(item.priority, priority);
            }

            scan(artifact, stack);
        }

        stack.pop();
    }

}
