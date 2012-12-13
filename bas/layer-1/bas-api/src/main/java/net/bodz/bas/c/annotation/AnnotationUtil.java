package net.bodz.bas.c.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @test AnnotationUtilTest
 */
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
     * @see net.bodz.bas.c.object.lang.Nullables#getAnnotationValue(Annotation, Class)
     * @see net.bodz.bas.c.object.lang.Nullables#getAnnotationValue(Annotation, Object)
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
     * @see net.bodz.bas.c.object.lang.Nullables#getAnnotationValue(Annotation, Class)
     * @see net.bodz.bas.c.object.lang.Nullables#getAnnotationValue(Annotation, Object)
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

    /**
     * @return <code>null</code> If specified annotation type isn't declared on the annotation
     *         element.
     */
    public static <T extends Annotation> T getDeclaredAnnotation(AnnotatedElement annotatedElement,
            Class<T> annotationType) {
        // return annotatedElement.getDeclaredAnnotation(annotationType);
        Annotation[] declv = annotatedElement.getDeclaredAnnotations();
        for (int i = 0; i < declv.length; i++) {
            Annotation _a = declv[i];
            if (annotationType.equals(_a.annotationType()))
                return annotationType.cast(_a);
        }
        return null;
    }

}
