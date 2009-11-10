package net.bodz.bas.lang.ref;

import java.lang.reflect.AnnotatedElement;

public interface VarMeta extends AnnotatedElement {

    String getName();

    Class<?> getType();

    int getModifiers();

    boolean isReadOnly();

    boolean hasPropertyChangeSupport();

}
