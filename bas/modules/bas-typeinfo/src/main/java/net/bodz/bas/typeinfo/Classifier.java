package net.bodz.bas.typeinfo;

import java.util.Map;

import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.types.Negotiation;

public interface Classifier<T> {

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, Negotiation negotiation)
            throws ClassifyException, NegotiationException;

    Annotations getClassAnnotations(String classId);

}
