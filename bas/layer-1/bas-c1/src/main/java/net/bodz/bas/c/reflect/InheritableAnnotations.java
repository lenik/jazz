package net.bodz.bas.c.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.c.reflect.query.ReflectQuery;

public class InheritableAnnotations {

    /**
     * Get all declared annotations for all methods with the same signature of the specific class
     * and its ancestors.
     * 
     * There maybe multiple annotations with the same annotation type in the result list.
     * 
     * @param clazz
     *            The start class to search, stopped at <code>Object.class</code>.
     * @param methodName
     *            The name of the method whose declare annotations are to be searched.
     * @param parameterTypes
     *            The parameter types of the method whose declared annotations are to be searched.
     * @return The list of all declared annotations. The order is: The annotations on the overrided
     *         method comes first, then the annotations on the method in the super classes.
     */
    public static List<Annotation> getDeclaredAnnotations(Class<?> clazz, String methodName, Class<?>... parameterTypes) {

        List<Annotation> annotations = new ArrayList<Annotation>();

        // In the order of: overrided-method --> super-method.
        for (Method declaredMethod : ReflectQuery //
                .selectDeclaredMethods(clazz) //
                // .stopAt(stopClass) //
                .nameEquals(methodName) //
                .parametersEquals(parameterTypes)) {

            annotations.addAll(Arrays.asList(declaredMethod.getDeclaredAnnotations()));
        }

        return annotations;
    }

    /**
     * Get declared annotations of the specific type for all methods with the same signature of the
     * specific class and its ancestors.
     * 
     * There maybe multiple annotations with the same annotation type in the result list.
     * 
     * @param annotationType
     *            The specific annotation type.
     * @param clazz
     *            The start class to search, stopped at <code>Object.class</code>.
     * @param methodName
     *            The name of the method whose declare annotations are to be searched.
     * @param parameterTypes
     *            The parameter types of the method whose declared annotations are to be searched.
     * @return The list of declared annotations. The order is: The annotations on the overrided
     *         method comes first, then the annotations on the method in the super classes.
     */
    public static <A extends Annotation> List<A> getDeclaredAnnotations(Class<A> annotationType, Class<?> clazz,
            String methodName, Class<?>... parameterTypes) {

        List<A> annotations = new ArrayList<A>();

        // In the order of: overrided-method --> super-method.
        for (Method declaredMethod : ReflectQuery //
                .selectDeclaredMethods(clazz) //
                // .stopAt(stopClass) //
                .nameEquals(methodName) //
                .parametersEquals(parameterTypes)) {

            for (Annotation declaredAnnotation : declaredMethod.getDeclaredAnnotations()) {
                if (annotationType.equals(declaredAnnotation.annotationType())) {
                    @SuppressWarnings("unchecked")
                    A _a = (A) declaredAnnotation;
                    annotations.add(_a);
                }
            }
        }

        return annotations;
    }

}
