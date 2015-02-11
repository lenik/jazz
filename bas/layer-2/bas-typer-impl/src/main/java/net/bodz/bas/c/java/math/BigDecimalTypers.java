package net.bodz.bas.c.java.math;

import java.math.BigDecimal;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IllegalParameterUsageException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class BigDecimalTypers
        extends AbstractCommonTypers<BigDecimal> {

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
        String distribution = options.get(sampleDistribution, defaultSampleDistribution);
        switch (distribution) {
        case normalSampleDistribution:
            return BigDecimal.valueOf(prng.nextDouble());
        case gaussianSampleDistribution:
            return BigDecimal.valueOf(prng.nextGaussian());
        default:
            throw new IllegalParameterUsageException(options.getOption(sampleDistribution));
        }
    }

}
