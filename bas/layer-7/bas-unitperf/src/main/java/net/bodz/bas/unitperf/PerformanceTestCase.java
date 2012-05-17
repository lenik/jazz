package net.bodz.bas.unitperf;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.Option;
import net.bodz.bas.traits.ISampleGenerator;

import org.junit.Assert;

public class PerformanceTestCase
        extends Assert {

    IParameter testUsage = new Option(ISampleGenerator.sampleUsage, "test");

}
