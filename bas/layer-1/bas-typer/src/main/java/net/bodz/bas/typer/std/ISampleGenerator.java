package net.bodz.bas.typer.std;

import java.util.Map;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;

public interface ISampleGenerator<T>
        extends IStdTyper {

    int typerIndex = 0xa5d35f7a; // ISampleGenerator

    /**
     * @return non-<code>null</code> sample instance.
     */
    T newSample()
            throws CreateException;

    /**
     * A short string phrase describe the usage.
     * 
     * Examples:
     * <ul>
     * <li><code>"use-case"</code> for demo presentation (default)</li>
     * <li><code>"test"</code> for Unit Test</li>
     * <li><code>"performance"</code> for Performance Test</li>
     * </ul>
     */
    @ParameterType(String.class)
    String sampleUsage = "sample.usage";

    @ParameterType(Map.class)
    String sampleClassification = "sample.classification";

    /**
     * Negotiations:
     * <ul>
     * <li>Optional {@link #sampleUsage}: Generate samples for specified usage.
     * <li>Optional {@link #sampleClass}: Generate samples of specified classification.
     * <li>Optional {@link Random}.class: Build random samples rather than meaningful ones, with the
     * specified {@link Random} generator when possible.
     * </ul>
     * 
     * @return <code>null</code> if the sample space restricted by <code>classification</code> is
     *         empty.
     */
    T newSample(IOptions options)
            throws CreateException;

}
