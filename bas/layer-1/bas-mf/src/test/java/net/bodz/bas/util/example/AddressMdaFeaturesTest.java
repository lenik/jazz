package net.bodz.bas.util.example;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.mf.std.ICommonMdaFeatures;
import net.bodz.bas.mf.std.IFormatter;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.t.pojo.eg.Address;
import net.bodz.bas.t.pojo.eg.CountryAliasUtil;
import net.bodz.bas.t.pojo.eg.PostCodeUtil;

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
        IOptions options = new Options() //
                .addOption(new CountryAliasUtil()) //
                .addOption(new PostCodeUtil());

        String input = "cn:310000:somewhere";
        String expected = "China:Zhejiang:somewhere";
        Address address = addressParser.parse(input, options);
        String actual = addressFormatter.format(address);
        assertEquals(expected, actual);
    }

}
