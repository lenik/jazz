package net.bodz.bas.t.pojo.eg;

public class CountryAliasUtil {

    public String unalias(String alias) {
        if ("cn".equals(alias))
            return "China";
        return alias;
    }

}
