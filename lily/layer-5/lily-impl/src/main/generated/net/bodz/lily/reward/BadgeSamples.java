package net.bodz.lily.reward;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class BadgeSamples
        extends TestSampleBuilder {

    @Override
    public Badge build()
            throws Exception {
        Badge a = new Badge();
        a.setId(761431277);
        a.setExpr("Eeeag oha bv ibdc, oyikaue. iemfoi, Reea oja.");
        a.setVal(635007912);
        a.setDescend(true);
        a.setTransient_(true);
        a.setIndexed(false);
        return a;
    }

    @Override
    public BadgeSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public Badge buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
