package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Map;

public interface IAnnotated
        extends AnnotatedElement {

    Map<Class<?>, Annotation> getAnnotationMap();

    Map<Class<?>, Annotation> getDeclaredAnnotationMap();

    void dumpAnnotations(Map<Class<?>, Annotation> map);

}
