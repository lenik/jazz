package net.bodz.bas.annotation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.lang.Nullables;

public class AnnotationUtil {

    /**
     * This is the same to
     * 
     * <pre>
     * findAnnotation(annotationClass, ReflectQuery.selectClassChain(startClass))
     * </pre>
     * 
     * but slightly faster.
     * 
     * <p>
     * If there are more than one occurence, return the first occurence.
     * 
     * @throws NullPointerException
     *             If <code>annotationClass</code> or <code>startClass</code> is <code>null</code>.
     * @see Nullables#getAnnotationValue(Annotation, Class)
     * @see Nullables#getAnnotationValue(Annotation, Object)
     */
    public static <A extends Annotation> A findAnnotation(Class<A> annotationClass, Class<?> startClass,
            Class<?> stopClass) {
        if (startClass == null)
            throw new NullPointerException("startClass");
        while (startClass != null) {
            if (stopClass != null && stopClass == startClass)
                break;
            A annotation = startClass.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
            startClass = startClass.getSuperclass();
        }
        return null;
    }

    /**
     * If there are more than one occurence, return the first occurence.
     * 
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     * @see Nullables#getAnnotationValue(Annotation, Class)
     * @see Nullables#getAnnotationValue(Annotation, Object)
     */
    public static <A extends Annotation> A findAnnotation(Class<A> annotationClass,
            Iterable<? extends AnnotatedElement> many) {
        for (AnnotatedElement element : many) {
            A annotation = element.getAnnotation(annotationClass);
            if (annotation != null)
                return annotation;
        }
        return null;
    }

}
