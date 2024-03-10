package net.bodz.lily.schema.reward;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class BadgeSamples
        extends TestSampleBuilder {

    @Override
    public Badge build()
            throws Exception {
        Badge a = new Badge();
        a.setExpr("Eeeag oha bv ibdc, oyikaue. iemfoi, Reea oja.");
        a.setVal(761431277);
        a.setDescend(false);
        a.setTransient_(true);
        a.setIndexed(true);
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
