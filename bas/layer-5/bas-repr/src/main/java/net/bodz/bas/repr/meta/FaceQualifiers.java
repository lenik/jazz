package net.bodz.bas.repr.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Qualifier;

import net.bodz.bas.inject.Qualifiers;

public class FaceQualifiers
        extends Qualifiers {

    public static List<String> getQualifierNames(AnnotatedElement el) {
        List<String> names = new ArrayList<>();
        for (Annotation a : el.getAnnotations()) {
            if (a instanceof Face) {
                Face aFace = (Face) a;
                for (String name : aFace.value())
                    names.add(name);
            } else {
                Class<?> aClass = a.getClass();
                if (aClass.isAnnotationPresent(Qualifier.class)) {
                    String qName = aClass.getName();
                    Named aNamed = aClass.getAnnotation(Named.class);
                    if (aNamed != null)
                        qName = aNamed.value();
                    names.add(qName);
                }
            }
        }
        return names;
    }

}
