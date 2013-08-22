package net.bodz.bas.mf.spi;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.mf.std.IParser;

public class BasCompanionMdaFeaturesProviderTest
        extends Assert {

    @Test
    public void getIntegerMdaFeatures() {
        BasCompanionMdaFeaturesProvider provider = new BasCompanionMdaFeaturesProvider();
        IParser<Integer> parser = provider.getMdaFeature(Integer.class, IParser.class);
        assertNotNull(parser);
    }

}
