package net.bodz.bas.typer.std;

public interface IBasicTyperFamily<T>
        extends ITyperFamily<T>, IStdTyper {

    int typerIndex = 0xd5c04a20; // IBasicTyperFamily

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
    ITextFormat<T> getTextForm();

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
