package net.bodz.bas.util.example;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.tf.TypeFeatures;
import net.bodz.bas.tf.std.ICommonTypeFeatures;
import net.bodz.bas.tf.std.IFormatter;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.t.pojo.eg.Address;
import net.bodz.bas.t.pojo.eg.CountryAliasUtil;
import net.bodz.bas.t.pojo.eg.PostCodeUtil;

public class AddressTypeFeaturesTest
        extends Assert {

    ICommonTypeFeatures<Address> addressTypeFeatures = TypeFeatures.getTypeFeature(Address.class, ICommonTypeFeatures.class);

    IParser<Address> addressParser = addressTypeFeatures.getParser();
    IFormatter<Address> addressFormatter = addressTypeFeatures.getFormatter();

    IParser<Address> strictAddressParser = new StrictAddressTypeFeatures().getParser();

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
