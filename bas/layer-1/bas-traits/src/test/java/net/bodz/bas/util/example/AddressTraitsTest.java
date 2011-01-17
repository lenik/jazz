package net.bodz.bas.util.example;

import net.bodz.bas.lang.negotiation.FinalNegotiation;
import net.bodz.bas.lang.negotiation.MandatoryException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.traits.ICommonTraits;
import net.bodz.bas.traits.IFormatter;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.Traits;
import net.bodz.bas.util.example.AddressTraits.CountryAliasUtil;
import net.bodz.bas.util.example.AddressTraits.PostCodeUtil;

import org.junit.Assert;
import org.junit.Test;

public class AddressTraitsTest
        extends Assert {

    @SuppressWarnings("unchecked")
    ICommonTraits<Address> addressTraits = Traits.getTraits(Address.class, ICommonTraits.class);

    IParser<Address> addressParser = addressTraits.getParser();
    IFormatter<Address> addressFormatter = addressTraits.getFormatter();

    IParser<Address> strictAddressParser = new StrictAddressTraits().getParser();

    @Test
    public void testDefaultNoChange()
            throws Exception {
        String input = "cn:310000:somewhere";
        Address address = addressParser.parse(input);
        String actual = addressFormatter.format(address);
        assertEquals(input, actual);
    }

    @Test
    public void testDefaultCountryPost()
            throws Exception {
        FinalNegotiation negotiation = new FinalNegotiation(//
                new NegotiationParameter(new CountryAliasUtil()), //
                new NegotiationParameter(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = addressParser.parse(input, negotiation);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

    @Test(expected = MandatoryException.class)
    public void testMandatoryFail()
            throws Exception {
        FinalNegotiation negotiation = new FinalNegotiation(//
                new NegotiationParameter(new CountryAliasUtil()), //
                new NegotiationParameter(new PostCodeUtil(), true));

        String input = "cn:310000:somewhere";
        addressParser.parse(input, negotiation);
    }

    public void testMandatoryOK()
            throws Exception {
        FinalNegotiation negotiation = new FinalNegotiation(//
                new NegotiationParameter(new CountryAliasUtil()), //
                new NegotiationParameter(new PostCodeUtil(), true));

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = strictAddressParser.parse(input, negotiation);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

}
