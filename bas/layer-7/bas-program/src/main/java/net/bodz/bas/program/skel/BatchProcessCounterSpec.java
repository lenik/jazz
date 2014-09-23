package net.bodz.bas.program.skel;

import net.bodz.bas.util.stat.AbstractCounterSpec;
import net.bodz.bas.util.stat.CounterDef;
import net.bodz.bas.util.stat.ICounterDef;

public class BatchProcessCounterSpec
        extends AbstractCounterSpec {

    ICounterDef<Long> totalDef;
    ICounterDef<Long> ignoredDef;
    ICounterDef<Long> savedDef;
    ICounterDef<Long> sameDef;
    ICounterDef<Long> erroredDef;
    ICounterDef<Long> errorsDef;
    ICounterDef<Long> copiedDef;
    ICounterDef<Long> movedDef;
    ICounterDef<Long> renamedDef;
    ICounterDef<Long> deletedDef;

    public BatchProcessCounterSpec() {
        totalDef = CounterDef.create("total", long.class);
        ignoredDef = CounterDef.create("ignored", long.class);
        savedDef = CounterDef.create("saved", long.class);
        sameDef = CounterDef.create("same", long.class);
        erroredDef = CounterDef.create("errored", long.class);
        errorsDef = CounterDef.create("errors", long.class);
        copiedDef = CounterDef.create("copied", long.class);
        movedDef = CounterDef.create("moved", long.class);
        renamedDef = CounterDef.create("renamed", long.class);
        deletedDef = CounterDef.create("deleted", long.class);

        addDefinition(totalDef);
        addDefinition(ignoredDef);
        addDefinition(savedDef);
        addDefinition(sameDef);
        addDefinition(erroredDef);
        addDefinition(errorsDef);
        addDefinition(copiedDef);
        addDefinition(movedDef);
        addDefinition(renamedDef);
        addDefinition(deletedDef);
    }

}
