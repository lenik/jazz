package net.bodz.bas.traits;

import net.bodz.bas.lang.IQueryable;

public interface ICommonTraits<T>
        extends IQueryable {

    int traitsIndex = 365443289; // ICommonTraits

    /**
     * Get the type attributes.
     * 
     * @return <code>null</code> If the type attributes is not available.
     */
    IAttributes getAttributes();

    /**
     * Get the type parser.
     * 
     * @return <code>null</code> If the type parser is not available.
     */
    IParser<T> getParser();

    /**
     * Get the type formatter.
     * 
     * @return <code>null</code> If the type formatter is not available.
     */
    IFormatter<T> getFormatter();

    /**
     * Get the text form of the type.
     * 
     * @return <code>null</code> If the type form is not available.
     */
    ITextForm<T> getTextForm();

    /**
     * Get the type classifier..
     * 
     * @return <code>null</code> If the type classifier is not available.
     */
    IClassifier<T> getClassifier();

    /**
     * Get the type content searcher..
     * 
     * @return <code>null</code> If the type content searcher is not available.
     */
    ISearcher<T> getSearcher();

    /**
     * Get the type validator.
     * 
     * @return <code>null</code> If the type validator is not available.
     */
    IValidator<T> getValidator();

    /**
     * Get the type instance store.
     * 
     * @return <code>null</code> If the type instance store is not available.
     */
    IInstanceStore<T> getInstanceStore();

    /**
     * Get the type sample generator.
     * 
     * @return <code>null</code> If the type sample generator is not available.
     */
    ISampleGenerator<T> getSampleGenerator();

}
