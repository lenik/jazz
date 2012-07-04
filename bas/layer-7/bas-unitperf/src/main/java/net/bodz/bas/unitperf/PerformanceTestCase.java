package net.bodz.bas.unitperf;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.Negotiation;
import net.bodz.bas.traits.ISampleGenerator;

import org.junit.Assert;

public class PerformanceTestCase
        extends Assert {

    IParameter testUsage = Negotiation.option(ISampleGenerator.sampleUsage, "test");

}
