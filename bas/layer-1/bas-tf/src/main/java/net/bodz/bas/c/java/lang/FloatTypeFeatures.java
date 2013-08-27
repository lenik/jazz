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

public class FloatTypeFeatures
        extends AbstractCommonTypeFeatures<Float> {

    /**
     * The distribution algorithm used to generate samples.
     * 
     * @see #normalSampleDistribution
     * @see #gaussianSampleDistribution
     */
    @ParameterType(String.class)
    public static final String sampleDistribution = DoubleTypeFeatures.sampleDistribution;
    public static final String normalSampleDistribution = DoubleTypeFeatures.normalSampleDistribution;
    public static final String gaussianSampleDistribution = DoubleTypeFeatures.gaussianSampleDistribution;
    public static final String defaultSampleDistribution = DoubleTypeFeatures.defaultSampleDistribution;

    public FloatTypeFeatures() {
        super(Float.class);
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
    public Float parse(String text)
            throws ParseException {
        try {
            return Float.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Float newSample()
            throws CreateException {
        return random.nextFloat();
    }

    @Override
    public Float newSample(Map<String, Object> classification, IOptions options)
            throws CreateException {
        String distribution = options.get(sampleDistribution, defaultSampleDistribution);

        if (normalSampleDistribution.equals(distribution))
            return random.nextFloat();
        else if (gaussianSampleDistribution.equals(distribution))
            return (float) random.nextGaussian();
        else
            throw new IllegalParameterUsageException(options.getOption(sampleDistribution));
    }

}
