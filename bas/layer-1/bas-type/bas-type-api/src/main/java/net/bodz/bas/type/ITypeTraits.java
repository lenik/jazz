package net.bodz.bas.type;

import net.bodz.bas.lang.IQueryable;
import net.bodz.bas.type.traits.IAttributes;
import net.bodz.bas.type.traits.IClassifier;
import net.bodz.bas.type.traits.IFormatter;
import net.bodz.bas.type.traits.IInstanceStore;
import net.bodz.bas.type.traits.IParser;
import net.bodz.bas.type.traits.ISampleGenerator;
import net.bodz.bas.type.traits.ISearcher;
import net.bodz.bas.type.traits.ITextForm;
import net.bodz.bas.type.traits.IValidator;

public interface ITypeTraits<T>
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
