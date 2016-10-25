package net.bodz.bas.util.stat;

import net.bodz.bas.util.stat.AbstractCounterSpec;
import net.bodz.bas.util.stat.CounterDef;
import net.bodz.bas.util.stat.ICounterDef;

public class TestCounterSpec
        extends AbstractCounterSpec {

    ICounterDef<Long> totalDef;
    ICounterDef<Integer> delayedDef;

    public TestCounterSpec() {
        totalDef = CounterDef.create("total", long.class);
        delayedDef = CounterDef.create("delayed", int.class);
        addDefinition(totalDef);
        addDefinition(delayedDef);
    }

}
