package net.bodz.bas.mf.std;

import java.util.Map;

import net.bodz.bas.rtx.IOptions;

public interface IClassifier<T> {

    int mdaFeatureIndex = -616435894; // IClassifier

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, IOptions options)
            throws ClassifyException;

    IAttributes getClassAnnotations(String classId);

}
