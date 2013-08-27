package net.bodz.bas.tf.std;

import java.util.Map;

import net.bodz.bas.rtx.IOptions;

public interface IClassifier<T> {

    int typeFeatureIndex = -616435894; // IClassifier

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, IOptions options)
            throws ClassifyException;

    IAttributes getClassAnnotations(String classId);

}
