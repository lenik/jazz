package net.bodz.bas.util.example;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.MandatoryException;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IFormatter;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.exception.ParseException;

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
    public IParser<Address> getParser() {
        return this;
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
            for (NegotiationParameter param : negotiation) {
                if (param.accept(CountryAliasUtil.class))
                    country = param.<CountryAliasUtil> value().unalias(country);
                else if (param.accept(PostCodeUtil.class, resolvePostCode)) {
                    postCode = Integer.parseInt(city);
                    city = param.<PostCodeUtil> value().getCityFromCode(postCode);
                } else
                    param.bypass();
            }
        }

        return new Address(address, city, country, postCode, null);
    }

    @Override
    public IFormatter<Address> getFormatter() {
        return this;
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
