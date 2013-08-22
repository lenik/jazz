package net.bodz.bas.util.example;

import static net.bodz.bas.rtx.Negotiation.*;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.rtx.MandatoryException;
import net.bodz.bas.t.pojo.eg.Address;
import net.bodz.bas.t.pojo.eg.CountryAliasUtil;
import net.bodz.bas.t.pojo.eg.PostCodeUtil;
import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.mf.std.ICommonMdaFeatures;
import net.bodz.bas.mf.std.IFormatter;
import net.bodz.bas.mf.std.IParser;

public class AddressMdaFeaturesTest
        extends Assert {

    ICommonMdaFeatures<Address> addressMdaFeatures = MdaFeatures.getMdaFeature(Address.class, ICommonMdaFeatures.class);

    IParser<Address> addressParser = addressMdaFeatures.getParser();
    IFormatter<Address> addressFormatter = addressMdaFeatures.getFormatter();

    IParser<Address> strictAddressParser = new StrictAddressMdaFeatures().getParser();

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
        INegotiation negotiation = list(//
                option(new CountryAliasUtil()), //
                option(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = addressParser.parse(input, negotiation);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

    @Test(expected = MandatoryException.class)
    public void testMandatoryFail()
            throws Exception {
        INegotiation negotiation = list(//
                option(new CountryAliasUtil()), //
                parameter(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        addressParser.parse(input, negotiation);
    }

    public void testMandatoryOK()
            throws Exception {
        INegotiation negotiation = list(//
                option(new CountryAliasUtil()), //
                parameter(new PostCodeUtil()));

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = strictAddressParser.parse(input, negotiation);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

}
