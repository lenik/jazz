package net.bodz.bas.unitperf;

import junit.framework.TestCase;
import net.bodz.bas.api.types.FinalNegotiation;
import net.bodz.bas.api.types.NegotiationParameter;
import net.bodz.bas.typeinfo.SampleGenerator;

public class PerformanceTestCase
        extends TestCase {
    {
        FinalNegotiation fn = new FinalNegotiation(//
                new NegotiationParameter(SampleGenerator.SAMPLE_USAGE, "test"));

    }
}
