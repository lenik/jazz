package net.bodz.bas.unitperf;

import org.junit.Assert;

import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.ListOptions;
import net.bodz.bas.typer.std.ISampleGenerator;

public class PerformanceTestCase
        extends Assert {

    IOptions testUsage = new ListOptions().addOption(ISampleGenerator.sampleUsage, "test");

}
