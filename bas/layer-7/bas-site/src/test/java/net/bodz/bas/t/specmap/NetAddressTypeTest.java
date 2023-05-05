package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class NetAddressTypeTest
        extends Assert {

    @Test
    public void testIPv4()
            throws ParseException {
        assertEquals(EndpointType.IPv4, //
                Endpoint.parse("1.2.3.4").type);
        assertEquals(EndpointType.IPv4, //
                Endpoint.parse("0.0.0.0").type);
    }

    @Test
    public void testIPv4_largeNum()
            throws ParseException {
        assertNotEquals(EndpointType.IPv4, //
                Endpoint.parse("256.0.0.0").type);
        assertNotEquals(EndpointType.IPv4, //
                Endpoint.parse("0.0.0.256").type);
    }

    @Test
    public void testIPv6()
            throws ParseException {
        assertEquals(EndpointType.IPv6, //
                Endpoint.parse("1:2:3:4:5:6:7:8").type);
        assertEquals(EndpointType.IPv6, //
                Endpoint.parse("::1").type);
        assertEquals(EndpointType.IPv6, //
                Endpoint.parse("1::").type);
    }

    @Test
    public void testIPv6_largeNum()
            throws ParseException {
        assertNotEquals(EndpointType.IPv6, //
                Endpoint.parse("10000::").type);
        assertNotEquals(EndpointType.IPv6, //
                Endpoint.parse("::10000").type);
    }

}
