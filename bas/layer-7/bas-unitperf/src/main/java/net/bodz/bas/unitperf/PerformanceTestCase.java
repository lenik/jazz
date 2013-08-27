package net.bodz.bas.unitperf;

import org.junit.Assert;

import net.bodz.bas.tf.std.ISampleGenerator;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;

public class PerformanceTestCase
        extends Assert {

    IOptions testUsage = new Options().addOption(ISampleGenerator.sampleUsage, "test");

}
