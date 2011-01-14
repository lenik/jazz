package net.bodz.bas.traits;

import net.bodz.bas.lang.IQueryable;

public interface ICommonTraits<T>
        extends IQueryable {

    // inheritable
    IAttributes getAttributes();

    IParser<T> getParser();

    // inheritable
    IFormatter<T> getFormatter();

    // semi-inheritable
    ITextForm<T> getTextForm();

    // inheritable
    IClassifier<T> getClassifier();

    // inheritable
    ISearcher<T> getSearcher();

    // inheritable
    IValidator<T> getValidator();

    IInstanceStore<T> getInstanceStore();

    ISampleGenerator<T> getSampleGenerator();

}
