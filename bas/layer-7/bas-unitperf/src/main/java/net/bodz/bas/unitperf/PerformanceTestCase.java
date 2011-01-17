package net.bodz.bas.unitperf;

import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.traits.ISampleGenerator;

import org.junit.Assert;

public class PerformanceTestCase
        extends Assert {

    NegotiationParameter testUsage = new NegotiationParameter(ISampleGenerator.sampleUsage, "test");

}
