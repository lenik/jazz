package net.bodz.bas.trait.spi;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.traits.IParser;

public class BasCompanionTraitsProviderTest
        extends Assert {

    @Test
    public void getIntegerTraits() {
        BasCompanionTraitsProvider provider = new BasCompanionTraitsProvider();
        IParser parser = provider.getTrait(Integer.class, IParser.class);
        assertNotNull(parser);
    }

}
