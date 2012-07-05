package net.bodz.bas.util.example;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.MandatoryException;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IFormatter;
import net.bodz.bas.traits.IParser;

public class AddressTraits
        extends AbstractCommonTraits<Address> {

    private final boolean resolvePostCode;

    public AddressTraits() {
        this(false);
    }

    public AddressTraits(boolean resolvePostCode) {
        super(Address.class);
        this.resolvePostCode = resolvePostCode;
    }

    @Override
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
        case IFormatter.traitIndex:
            return this;
        }
        return null;
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
    public Address parse(String text, INegotiation negotiation)
            throws ParseException, MandatoryException {
        String[] segs = text.split(":", 3);
        if (segs.length < 3)
            throw new ParseException("Address format=COUNTRY:POSTCODE:ADDRESS");

        String country = segs[0];
        String city = segs[1];
        String address = segs[2];
        int postCode = 0;

        if (negotiation != null) {
            CountryAliasUtil countryAliasUtil = negotiation.get(CountryAliasUtil.class);
            if (countryAliasUtil != null)
                country = countryAliasUtil.unalias(country);
            if (resolvePostCode) {
                PostCodeUtil postCodeUtil = negotiation.get(PostCodeUtil.class);
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

    static class CountryAliasUtil {
        public String unalias(String alias) {
            if ("cn".equals(alias))
                return "China";
            return alias;
        }
    }

    static class PostCodeUtil {
        public String getCityFromCode(int code) {
            if (code == 310000)
                return "Zhejiang";
            throw new IllegalArgumentException("Invalid post code: " + code);
        }
    }

}
