package net.bodz.bas.unitperf;

import org.junit.Assert;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.Negotiation;
import net.bodz.bas.traits.ISampleGenerator;

public class PerformanceTestCase
        extends Assert {

    IParameter testUsage = Negotiation.option(ISampleGenerator.sampleUsage, "test");

}
