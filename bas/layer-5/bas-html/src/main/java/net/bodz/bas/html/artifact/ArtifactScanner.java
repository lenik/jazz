package net.bodz.bas.html.artifact;

import java.util.*;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ArtifactScanner
        implements IArtifactVisitor {

    static final Logger logger = LoggerFactory.getLogger(ArtifactScanner.class);

    IArtifactManager artifactManager;

    /** scan this type only. */
    ArtifactType type;

    /** include optional or not */
    Boolean optional;

    int seq;

    Stack<IArtifactDependent> stack = new Stack<IArtifactDependent>();
    Map<IArtifact, OrderItem> closure = new LinkedHashMap<IArtifact, OrderItem>();

    public ArtifactScanner(IArtifactManager artifactManager, ArtifactType type, Boolean optional) {
        this.artifactManager = artifactManager;
        this.type = type;
        this.optional = optional;
    }

    public Set<IArtifact> getSortedResult() {
        List<OrderItem> list = new ArrayList<OrderItem>(closure.size());
        for (OrderItem item : closure.values())
            if (type == null || type == item.artifact.getType()) {
                list.add(item);
            }

        // Collections.sort(list, ItemComparator.INSTANCE);

        LinkedHashSet<IArtifact> artifacts = new LinkedHashSet<IArtifact>();
        for (OrderItem item : list)
            artifacts.add(item.artifact);
        return artifacts;
    }

    @Override
    public void begin(IArtifactDependent node) {
        if (stack.contains(node)) {
            logger.error("Detected dependency dead loop. n=" + stack.size());
            for (IArtifactDependent a : stack)
                logger.error("    item: " + a);
            logger.error("    error: " + node);
            return;
        }
        stack.push(node);
    }

    @Override
    public void end(IArtifactDependent dependent) {
        stack.pop();
    }

    @Override
    public void dependency(IArtifactDependency dependency) {
        if (optional != null && optional.booleanValue() != dependency.isOptional())
            return;

        int priority = dependency.getPriority();
        IArtifact dep = artifactManager.getArtifact(dependency);
        if (dep == null)
            throw new NoSuchElementException(dependency.toString());

        dep.accept(this);

        add(dep, priority);
    }

    @Override
    public void artifact(IArtifact artifact) {
        add(artifact, 0);
    }

    void add(IArtifact artifact, int priority) {
        OrderItem item = closure.get(artifact);
        if (item == null) {
            item = new OrderItem();
            closure.put(artifact, item);
        }
        item.update(artifact, priority, stack.size());
    }

}
