package net.bodz.bas.t.pojo.eg;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IFormatter;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.rtx.IOptions;

public class AddressMdaFeatures
        extends AbstractCommonMdaFeatures<Address> {

    private final boolean resolvePostCode;

    public AddressMdaFeatures() {
        this(false);
    }

    public AddressMdaFeatures(boolean resolvePostCode) {
        super(Address.class);
        this.resolvePostCode = resolvePostCode;
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
        case IFormatter.mdaFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public Address parse(String text)
            throws ParseException {
        return parse(text, null);
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
                postCode = Integer.parseInt(city);
                city = postCodeUtil.getCityFromCode(postCode);
            }
        }

        return new Address(address, city, country, postCode, null);
    }

    @Override
    public String format(Address a) {
        return a.getCountry() + ":" + a.getCity() + ":" + a.getAddress();
    }

}
