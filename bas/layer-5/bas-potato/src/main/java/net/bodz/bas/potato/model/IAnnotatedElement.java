package net.bodz.bas.potato.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Map;

public interface IAnnotatedElement
        extends AnnotatedElement {

    void findAnnotations(Map<Class<? extends Annotation>, Annotation> map);

}
