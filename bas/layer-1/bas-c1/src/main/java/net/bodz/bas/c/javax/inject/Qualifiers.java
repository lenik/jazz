package net.bodz.bas.c.javax.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Qualifier;

public class Qualifiers {

    public static List<Annotation> getQualifiers(AnnotatedElement el) {
        List<Annotation> aList = new ArrayList<Annotation>();
        for (Annotation a : el.getAnnotations()) {
            if (a.getClass().isAnnotationPresent(Qualifier.class))
                aList.add(a);
        }
        return aList;
    }

}
