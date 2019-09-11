package net.bodz.bas.c.java.lang;

import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IllegalParameterUsageException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class FloatTypers
        extends AbstractCommonTypers<Float> {

    /**
     * The distribution algorithm used to generate samples.
     *
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ParameterType(String.class)
    public static final String sampleDistribution = DoubleTypers.sampleDistribution;
    public static final String normalSampleDistribution = DoubleTypers.normalSampleDistribution;
    public static final String gaussianSampleDistribution = DoubleTypers.gaussianSampleDistribution;
    public static final String defaultSampleDistribution = DoubleTypers.defaultSampleDistribution;

    public FloatTypers() {
        super(Float.class);
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
    public Float parse(String text, IOptions options)
            throws ParseException {
        try {
            return Float.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Float newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        String distribution = options.get(sampleDistribution, defaultSampleDistribution);
        if (normalSampleDistribution.equals(distribution))
            return prng.nextFloat();
        if (gaussianSampleDistribution.equals(distribution))
            return (float) prng.nextGaussian();
        throw new IllegalParameterUsageException(options.getOption(sampleDistribution));
    }

}
