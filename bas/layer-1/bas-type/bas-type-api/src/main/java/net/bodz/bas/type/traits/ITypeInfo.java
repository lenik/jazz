package net.bodz.bas.type.traits;

import net.bodz.bas.lang.IQueryable;

public interface ITypeInfo<T>
        extends IQueryable {

    IAttributes getAttributes();

    IParser<T> getParser();

    IFormatter<T> getFormatter();

    ITextForm<T> getTextForm();

    IClassifier<T> getClassifier();

    ISearchable getSearchable();

    IValidator<T> getValidator();

    IInstanceStore<T> getInstanceStore();

    ISampleGenerator<T> getSampleGenerator();

}
