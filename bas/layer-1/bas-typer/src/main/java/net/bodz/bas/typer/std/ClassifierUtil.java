package net.bodz.bas.typer.std;

import java.util.Map;

import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.Typers;

public class ClassifierUtil {

    public static Map<String, Object> classify(Object object)
            throws ClassifyException {
        IClassifier<Object> classifier = Typers.getTyper(object, IClassifier.class);
        return classifier.classify(object);
    }

    public static Map<String, Object> classify(Object object, IOptions options)
            throws ClassifyException {
        IClassifier<Object> classifier = Typers.getTyper(object, IClassifier.class);
        return classifier.classify(object, options);
    }

    public static IAttributes getClassAnnotations(Class<?> objType, String classId) {
        IClassifier<Object> classifier = Typers.getTyper(objType, IClassifier.class);
        return classifier.getClassAnnotations(classId);
    }

}
