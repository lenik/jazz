package net.bodz.bas.tf.spi;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.tf.std.IParser;

public class BasCompanionTypeFeatureProviderTest
        extends Assert {

    @Test
    public void getIntegerTypeFeatures() {
        BasCompanionTypeFeatureProvider provider = new BasCompanionTypeFeatureProvider();
        IParser<Integer> parser = provider.getTypeFeature(Integer.class, IParser.class);
        assertNotNull(parser);
    }

}
