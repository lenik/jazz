package net.bodz.bas.traits;

import java.util.Map;

import net.bodz.bas.lang.negotiation.INegotiation;

public interface IClassifier<T> {

    int traitIndex = -616435894; // IClassifier

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, INegotiation negotiation)
            throws ClassifyException;

    IAttributes getClassAnnotations(String classId);

}
