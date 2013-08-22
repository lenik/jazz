package net.bodz.bas.unitperf;

import org.junit.Assert;

import net.bodz.bas.rtx.INegotiation.IParameter;
import net.bodz.bas.rtx.Negotiation;
import net.bodz.bas.mf.std.ISampleGenerator;

public class PerformanceTestCase
        extends Assert {

    IParameter testUsage = Negotiation.option(ISampleGenerator.sampleUsage, "test");

}
