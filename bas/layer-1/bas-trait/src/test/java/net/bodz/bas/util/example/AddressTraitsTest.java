package net.bodz.bas.util.example;

import net.bodz.bas.lang.negotiation.IndexedNegotiation;
import net.bodz.bas.lang.negotiation.MandatoryException;
import net.bodz.bas.lang.negotiation.Parameter;
import net.bodz.bas.lang.negotiation.Option;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.ICommonTraits;
import net.bodz.bas.traits.IFormatter;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.example.AddressTraits.CountryAliasUtil;
import net.bodz.bas.util.example.AddressTraits.PostCodeUtil;

import org.junit.Assert;
import org.junit.Test;

public class AddressTraitsTest
        extends Assert {

    ICommonTraits<Address> addressTraits = Traits.getTrait(Address.class, ICommonTraits.class);

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
        IndexedNegotiation negotiation = new IndexedNegotiation(//
                new Option(new CountryAliasUtil()), //
                new Option(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = addressParser.parse(input, negotiation);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

    @Test(expected = MandatoryException.class)
    public void testMandatoryFail()
            throws Exception {
        IndexedNegotiation negotiation = new IndexedNegotiation(//
                new Option(new CountryAliasUtil()), //
                new Parameter(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        addressParser.parse(input, negotiation);
    }

    public void testMandatoryOK()
            throws Exception {
        IndexedNegotiation negotiation = new IndexedNegotiation(//
                new Option(new CountryAliasUtil()), //
                new Parameter(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = strictAddressParser.parse(input, negotiation);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

}
