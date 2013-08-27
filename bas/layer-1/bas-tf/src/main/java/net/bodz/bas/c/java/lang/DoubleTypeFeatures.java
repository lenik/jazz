package net.bodz.bas.c.java.lang;

import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.tf.std.ISampleGenerator;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IllegalParameterUsageException;

public class DoubleTypeFeatures
        extends AbstractCommonTypeFeatures<Double> {

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

    public DoubleTypeFeatures() {
        super(Double.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        switch (typeFeatureIndex) {
        case IParser.typeFeatureIndex:
        case ISampleGenerator.typeFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public Double parse(String text)
            throws ParseException {
        try {
            return Double.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Double newSample()
            throws CreateException {
        return random.nextDouble();
    }

    @Override
    public Double newSample(Map<String, Object> classification, IOptions options)
            throws CreateException {
        String distribution = options.get(sampleDistribution, defaultSampleDistribution);

        if (normalSampleDistribution.equals(distribution))
            return random.nextDouble();
        else if (gaussianSampleDistribution.equals(distribution))
            return random.nextGaussian();
        else
            throw new IllegalParameterUsageException(options.getOption(sampleDistribution));
    }

}
