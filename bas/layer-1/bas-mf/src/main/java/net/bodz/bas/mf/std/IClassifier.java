package net.bodz.bas.mf.std;

import java.util.Map;

import net.bodz.bas.rtx.INegotiation;

public interface IClassifier<T> {

    int mdaFeatureIndex = -616435894; // IClassifier

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, INegotiation negotiation)
            throws ClassifyException;

    IAttributes getClassAnnotations(String classId);

}
