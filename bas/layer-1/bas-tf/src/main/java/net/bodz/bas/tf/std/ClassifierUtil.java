package net.bodz.bas.tf.std;

import java.util.Map;

import net.bodz.bas.tf.TypeFeatures;
import net.bodz.bas.rtx.IOptions;

public class ClassifierUtil {

    public static Map<String, Object> classify(Object object)
            throws ClassifyException {
        IClassifier<Object> classifier = TypeFeatures.getTypeFeature(object, IClassifier.class);
        return classifier.classify(object);
    }

    public static Map<String, Object> classify(Object object, IOptions options)
            throws ClassifyException {
        IClassifier<Object> classifier = TypeFeatures.getTypeFeature(object, IClassifier.class);
        return classifier.classify(object, options);
    }

    public static IAttributes getClassAnnotations(Class<?> objType, String classId) {
        IClassifier<Object> classifier = TypeFeatures.getTypeFeature(objType, IClassifier.class);
        return classifier.getClassAnnotations(classId);
    }

}
