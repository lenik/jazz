package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;

public class AddressTypers
        extends AbstractCommonTypers<Address> {

    private final boolean resolvePostCode;

    public AddressTypers() {
        this(false);
    }

    public AddressTypers(boolean resolvePostCode) {
        super(Address.class);
        this.resolvePostCode = resolvePostCode;
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case IFormatter.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public Address parse(String text, IOptions options)
            throws ParseException {
        String[] segs = text.split(":", 3);
        if (segs.length < 3)
            throw new ParseException("Address format=COUNTRY:POSTCODE:ADDRESS");

        String country = segs[0];
        String city = segs[1];
        String address = segs[2];
        int postCode = 0;

        if (options != null) {
            CountryAliasUtil countryAliasUtil = options.get(CountryAliasUtil.class);
            if (countryAliasUtil != null)
                country = countryAliasUtil.unalias(country);
            if (resolvePostCode) {
                PostCodeUtil postCodeUtil = options.get(PostCodeUtil.class);
                if (postCodeUtil != null) {
                    postCode = Integer.parseInt(city);
                    city = postCodeUtil.getCityFromCode(postCode);
                }
            }
        }

        return new Address(address, city, country, postCode, null);
    }

    @Override
    public String format(Address a, IOptions options) {
        CountryAliasUtil countryAliasUtil = options.get(CountryAliasUtil.class);
        PostCodeUtil postCodeUtil = options.get(PostCodeUtil.class);

        String country = a.getCountry();
        String city = a.getCity();
        String address = a.getAddress();
        int postCode = a.getPostCode();

        if (countryAliasUtil != null)
            country = countryAliasUtil.alias(country);
        if (postCodeUtil != null)
            postCode = postCodeUtil.getCodeFromCity(city);

        return country + ":" + (postCode != 0 ? postCode : city) + ":" + address;
    }

}
