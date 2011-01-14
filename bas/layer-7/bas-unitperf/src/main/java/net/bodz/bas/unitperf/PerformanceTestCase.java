package net.bodz.bas.unitperf;

import junit.framework.TestCase;
import net.bodz.bas.lang.FinalNegotiation;
import net.bodz.bas.lang.NegotiationParameter;
import net.bodz.bas.traits.ISampleGenerator;

public class PerformanceTestCase
        extends TestCase {
    {
        FinalNegotiation fn = new FinalNegotiation(//
                new NegotiationParameter(ISampleGenerator.SAMPLE_USAGE, "test"));

    }
}
