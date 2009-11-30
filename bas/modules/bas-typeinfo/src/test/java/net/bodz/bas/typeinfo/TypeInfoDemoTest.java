package net.bodz.bas.typeinfo;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.api.exceptions.NegotiationException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.exceptions.UnsupportedNegotiationException;
import net.bodz.bas.api.types.FinalNegotiation;
import net.bodz.bas.api.types.Negotiation;
import net.bodz.bas.api.types.NegotiationParameter;
import net.bodz.bas.typeinfo.Parser;
import net.bodz.bas.typeinfo.SelfDescribedTypeInfo;
import net.bodz.bas.typeinfo.TypeInfo;
import net.bodz.bas.typeinfo.TypeInfoUtil;

import org.junit.Test;

public class TypeInfoDemoTest {

    static class CountryAlias {
        public String unalias(String alias) {
            if ("cn".equals(alias))
                return "China";
            return alias;
        }
    }

    static class PostCode {
        public String getCity(String code) {
            if (code.equals("310000"))
                return "Zhejiang";
            throw new IllegalArgumentException("Invalid post code: " + code);
        }
    }

    static class Address {
        String country;
        String city;
        String location;

        public Address(String country, String city, String location) {
            this.country = country;
            this.city = city;
            this.location = location;
        }

        @Override
        public String toString() {
            return country + ":" + city + ":" + location;
        }

        private static TypeInfo typeInfo = new AddressTypeInfo(true);

        static TypeInfo getTypeInfo() {
            return typeInfo;
        }

    }

    static class AddressTypeInfo
            extends SelfDescribedTypeInfo
            implements Parser<Address> {

        private final boolean resolvePostCode;

        public AddressTypeInfo(boolean resolvePostCode) {
            this.resolvePostCode = resolvePostCode;
        }

        @Override
        public Address parse(String text)
                throws ParseException {
            try {
                return parse(text, null);
            } catch (NegotiationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        @Override
        public Address parse(String text, Negotiation negotiation)
                throws ParseException, NegotiationException {
            String[] segs = text.split(":", 3);
            if (segs.length < 3)
                throw new ParseException("Address format=COUNTRY:POSTCODE:ADDRESS");
            if (negotiation != null) {
                for (NegotiationParameter param : negotiation) {
                    if (param.isWanted(CountryAlias.class))
                        segs[0] = param.getValue(CountryAlias.class).unalias(segs[0]);
                    else if (param.isWanted(PostCode.class, resolvePostCode))
                        segs[1] = param.getValue(PostCode.class).getCity(segs[1]);
                    else
                        throw new UnsupportedNegotiationException(param);
                }
            }
            return new Address(segs[0], segs[1], segs[2]);
        }
    }

    static class PostAddressTypeInfo
            extends AddressTypeInfo {
        public PostAddressTypeInfo() {
            super(true);
        }
    }

    static TypeInfo postAddressType = (AddressTypeInfo) TypeInfoUtil.getTypeInfo(Address.class);
    static TypeInfo nonpostAddressType = new AddressTypeInfo(false);

    @Test
    public void testDefaultNoChange()
            throws Exception {
        Address address = (Address) postAddressType.query(Parser.class).parse("cn:310000:somewhere");
        assertEquals("cn:310000:somewhere", address.toString());
    }

    @Test
    public void testDefaultCountryPost()
            throws Exception {
        FinalNegotiation negotiation = new FinalNegotiation(//
                new NegotiationParameter(new CountryAlias()), //
                new NegotiationParameter(new PostCode()));
        Address address = (Address) postAddressType.query(Parser.class).parse("cn:310000:somewhere", negotiation);
        assertEquals("China:Zhejiang:somewhere", address.toString());
    }

    @Test(expected = UnsupportedNegotiationException.class)
    public void testMandatoryFail()
            throws Exception {
        FinalNegotiation negotiation = new FinalNegotiation(//
                new NegotiationParameter(new CountryAlias()), //
                new NegotiationParameter(new PostCode(), true));
        nonpostAddressType.query(Parser.class).parse("cn:310000:somewhere", negotiation);
    }

    @Test(expected = UnsupportedNegotiationException.class)
    public void testPostIgnored()
            throws Exception {
        FinalNegotiation negotiation = new FinalNegotiation(//
                new NegotiationParameter(new CountryAlias()), //
                new NegotiationParameter(new PostCode()));
        Address address = (Address) nonpostAddressType.query(Parser.class).parse("cn:310000:somewhere", negotiation);
        assertEquals("China:3100:somewhere", address.toString());
    }
}
