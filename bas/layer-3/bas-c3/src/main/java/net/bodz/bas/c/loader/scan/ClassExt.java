package net.bodz.bas.c.loader.scan;

import java.util.HashSet;
import java.util.Set;

public class ClassExt {

    Set<Class<?>> subclasses = new HashSet<Class<?>>();
    Set<Class<?>> implementations = new HashSet<Class<?>>();
    Set<Class<?>> annotatedClasses = new HashSet<Class<?>>();

    public boolean addSubclass(Class<?> subclass) {
        return subclasses.add(subclass);
    }

    public boolean addImpl(Class<?> impl) {
        return implementations.add(impl);
    }

    public boolean addAnnotated(Class<?> annotated) {
        return annotatedClasses.add(annotated);
    }

    @Override
    public String toString() {
        return String.format("%d subclass, %d impl, %d annotated class", subclasses.size(), implementations.size(),
                annotatedClasses.size());
    }

}
