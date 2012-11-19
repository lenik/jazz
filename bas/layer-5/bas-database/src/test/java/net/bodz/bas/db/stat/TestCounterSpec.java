package net.bodz.bas.db.stat;

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
