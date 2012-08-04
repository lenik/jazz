package net.bodz.swt.gui;

import java.lang.reflect.AnnotatedElement;

public interface VarMeta
        extends AnnotatedElement {

    String getName();

    Class<?> getType();

    int getModifiers();

    boolean isReadOnly();

    boolean hasPropertyChangeSupport();

}
