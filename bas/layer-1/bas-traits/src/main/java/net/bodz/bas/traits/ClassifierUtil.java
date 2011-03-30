package net.bodz.bas.traits;

import java.util.Map;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;

public class ClassifierUtil {

    public static Map<String, Object> classify(Object object)
            throws ClassifyException {
        IClassifier<Object> classifier = Traits.getTraits(object, IClassifier.class);
        return classifier.classify(object);
    }

    public static Map<String, Object> classify(Object object, INegotiation negotiation)
            throws ClassifyException, NegotiationException {
        IClassifier<Object> classifier = Traits.getTraits(object, IClassifier.class);
        return classifier.classify(object, negotiation);
    }

    public static IAttributes getClassAnnotations(Class<?> objType, String classId) {
        IClassifier<Object> classifier = Traits.getTraits(objType, IClassifier.class);
        return classifier.getClassAnnotations(classId);
    }

}
