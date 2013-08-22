package net.bodz.bas.repr.viz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface IViewBuilderFactory {

    <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type);

    /**
     * Not implemented well, yet.
     * 
     * @param type
     *            Raw type or parameterized type.
     * @param annotations
     *            Annotations on the property or field or method.
     */
    <T> IViewBuilder<T> getViewBuilder(Type type, Annotation[] annotations);

}
