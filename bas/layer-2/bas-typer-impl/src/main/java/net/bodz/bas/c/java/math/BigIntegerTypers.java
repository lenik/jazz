package net.bodz.bas.c.java.math;

import java.math.BigInteger;
import java.util.Random;

import net.bodz.bas.c.java.util.RandomFn;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class BigIntegerTypers
        extends AbstractCommonTypers<BigInteger> {

    /**
     * The min precision
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MIN_PRECISION = "sample.minPrecision";
    public static final int DEFAULT_MIN_PRECISION = 1;

    /**
     * The max precision
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MAX_PRECISION = "sample.maxPrecision";
    public static final int DEFAULT_MAX_PRECISION = 12;

    /**
     * The distribution algorithm used to generate samples.
     *
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ParameterType(String.class)
    public static final String sampleDistribution = "sample.distribution";
    public static final String normalSampleDistribution = "normal";
    public static final String defaultSampleDistribution = normalSampleDistribution;

    public BigIntegerTypers() {
        super(BigInteger.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
            case IParser.typerIndex:
            case ISampleGenerator.typerIndex:
                return this;
        }
        return null;
    }

    @Override
    public BigInteger parse(String text, IOptions options)
            throws ParseException {
        try {
            return new BigInteger(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public BigInteger newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);

        int minPrecision = options.getInt(OPTION_MIN_PRECISION, DEFAULT_MIN_PRECISION);
        int maxPrecision = options.getInt(OPTION_MAX_PRECISION, DEFAULT_MAX_PRECISION);

        int precision = minPrecision + prng.nextInt(maxPrecision - minPrecision);
        int intLen = precision;

        String str = RandomFn.digits(prng, intLen, true);
        return new BigInteger(str);
    }

}
