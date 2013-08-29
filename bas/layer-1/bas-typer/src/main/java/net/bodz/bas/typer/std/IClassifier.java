package net.bodz.bas.typer.std;

import java.util.Map;

import net.bodz.bas.rtx.IOptions;

public interface IClassifier<T>
        extends IStdTyper {

    int typerIndex = 0xf1b27ae6; // IClassifier

    Map<String, Object> classify(T object)
            throws ClassifyException;

    Map<String, Object> classify(T object, IOptions options)
            throws ClassifyException;

    IAttributes getClassAnnotations(String classId);

}
