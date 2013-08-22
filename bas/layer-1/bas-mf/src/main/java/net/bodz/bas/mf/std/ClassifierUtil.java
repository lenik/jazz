package net.bodz.bas.mf.std;

import java.util.Map;

import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.mf.MdaFeatures;

public class ClassifierUtil {

    public static Map<String, Object> classify(Object object)
            throws ClassifyException {
        IClassifier<Object> classifier = MdaFeatures.getMdaFeature(object, IClassifier.class);
        return classifier.classify(object);
    }

    public static Map<String, Object> classify(Object object, INegotiation negotiation)
            throws ClassifyException {
        IClassifier<Object> classifier = MdaFeatures.getMdaFeature(object, IClassifier.class);
        return classifier.classify(object, negotiation);
    }

    public static IAttributes getClassAnnotations(Class<?> objType, String classId) {
        IClassifier<Object> classifier = MdaFeatures.getMdaFeature(objType, IClassifier.class);
        return classifier.getClassAnnotations(classId);
    }

}
