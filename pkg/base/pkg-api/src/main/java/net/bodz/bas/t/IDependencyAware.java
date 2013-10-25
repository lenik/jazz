package net.bodz.bas.t;

import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.c.object.IdentityObjectSet;
import net.bodz.bas.t.iterator.RecIterator;

public interface IDependencyAware<node_t> {

    Collection<? extends node_t> getDependencies();

    Iterable<? extends node_t> dependencyClosure();

    class fn {

        public static <node_t extends IDependencyAware<node_t>> //
        Iterable<? extends node_t> dependencyClosure(final node_t start) {
            return new Iterable<node_t>() {

                @Override
                public Iterator<node_t> iterator() {
                    return new DependencyRecIterator<node_t>(start);
                }

            };
        }

    }

}

class DependencyRecIterator<node_t extends IDependencyAware<node_t>>
        extends RecIterator<node_t> {

    private IdentityObjectSet uniq = new IdentityObjectSet();

    public DependencyRecIterator(node_t start) {
        super(start.getDependencies().iterator());
    }

    @Override
    protected node_t fetch() {
        node_t elm = super.fetch();

        if (elm == null)
            throw new NullPointerException("null component in level " + getLastElementDepth());

        if (!uniq.add(elm))
            throw new IllegalStateException("Loop detected: " + elm);

        return elm;
    }

    @Override
    protected Iterator<? extends node_t> expand(node_t element) {
        Collection<? extends node_t> dependencies = element.getDependencies();
        if (dependencies.isEmpty())
            return null;
        else
            return dependencies.iterator();
    }

}