package net.bodz.bas.t.pojo.eg;

public class CountryAliasUtil {

    public String alias(String country) {
        if ("China".equals(country))
            return "cn";
        else
            return country;
    }

    public String unalias(String alias) {
        if ("cn".equals(alias))
            return "China";
        else
            return alias;
    }

}
