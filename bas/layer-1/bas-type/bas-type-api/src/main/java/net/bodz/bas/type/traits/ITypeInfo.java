package net.bodz.bas.type.traits;

import net.bodz.bas.lang.IQueryable;


public interface ITypeInfo<T>
        extends IQueryable {

    IAnnotations getAnnotations();

    IParser<T> getParser();

    IFormatter<T> getFormatter();

    ITextForm<T> getTextForm();

    IClassifier<T> getClassifier();

    ISearchable getSearchable();

    IValidator<T> getValidator();

    IInstanceStore<T> getInstanceStore();

    ISampleGenerator<T> getSampleGenerator();

}
