package net.bodz.bas.c.java.math;

import java.math.BigDecimal;
import java.util.Random;

import net.bodz.bas.c.java.util.RandomFn;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class BigDecimalTypers
        extends AbstractCommonTypers<BigDecimal> {

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
     * The min scale
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MIN_SCALE = "sample.minScale";
    public static final int DEFAULT_MIN_SCALE = 0;

    /**
     * The max scale
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MAX_SCALE = "sample.maxScale";
    public static final int DEFAULT_MAX_SCALE = 2;

    /**
     * The distribution algorithm used to generate samples.
     *
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ParameterType(String.class)
    public static final String sampleDistribution = "sample.distribution";
    public static final String normalSampleDistribution = "normal";
    public static final String gaussianSampleDistribution = "gaussian";
    public static final String defaultSampleDistribution = normalSampleDistribution;

    public BigDecimalTypers() {
        super(BigDecimal.class);
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
    public BigDecimal parse(String text, IOptions options)
            throws ParseException {
        try {
            return new BigDecimal(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public BigDecimal newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);

        int minPrecision = options.getInt(OPTION_MIN_PRECISION, DEFAULT_MIN_PRECISION);
        int maxPrecision = options.getInt(OPTION_MAX_PRECISION, DEFAULT_MAX_PRECISION);

        int minScale = options.getInt(OPTION_MIN_SCALE, DEFAULT_MIN_SCALE);
        int maxScale = options.getInt(OPTION_MAX_SCALE, DEFAULT_MAX_SCALE);

        int precision = minPrecision + prng.nextInt(maxPrecision - minPrecision);
        int scale = minScale + prng.nextInt(maxScale - minScale);
        if (scale >= precision)
            scale = precision - 1;
        if (scale < 0)
            scale = 0;

        int intLen = precision;
        if (scale != 0)
            intLen -= scale; // + 1 (dot);

        String str = RandomFn.digits(prng, intLen, true);
        if (scale != 0 && random.nextBoolean()) {
            str += "." + RandomFn.digits(prng, scale, false);
        }
        return new BigDecimal(str);
    }


}
