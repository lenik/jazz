package net.bodz.bas.traits;

import java.util.Map;

import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationException;

public interface IClassifier<T> {

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, INegotiation negotiation)
            throws ClassifyException, NegotiationException;

    IAttributes getClassAnnotations(String classId);

}
