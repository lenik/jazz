package net.bodz.bas.traits;

import java.util.Map;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;

public interface IClassifier<T> {

    int traitsIndex = -616435894; // IClassifier

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, INegotiation negotiation)
            throws ClassifyException, NegotiationException;

    IAttributes getClassAnnotations(String classId);

}
