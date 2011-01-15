package net.bodz.bas.unitperf;

import net.bodz.bas.lang.FinalNegotiation;
import net.bodz.bas.lang.NegotiationParameter;
import net.bodz.bas.traits.ISampleGenerator;

import org.junit.Assert;

public class PerformanceTestCase
        extends Assert {
    {
        FinalNegotiation fn = new FinalNegotiation(//
                new NegotiationParameter(ISampleGenerator.sampleUsage, "test"));

    }
}
