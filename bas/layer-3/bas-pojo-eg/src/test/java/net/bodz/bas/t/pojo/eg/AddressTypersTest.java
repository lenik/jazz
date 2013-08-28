package net.bodz.bas.t.pojo.eg;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IBasicTyperFamily;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;

public class AddressTypersTest
        extends Assert {

    IBasicTyperFamily<Address> addressTypers = Typers.getTyper(Address.class, IBasicTyperFamily.class);

    IParser<Address> addressParser = addressTypers.getParser();
    IFormatter<Address> addressFormatter = addressTypers.getFormatter();

    IParser<Address> strictAddressParser = new AddressTypers(true).getParser();

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
