package net.bodz.bas.i18n.dom;

public class DomainStrings {

    public static DomainString concat(DomainString... dstrings) {
        DomainString result = null;
        for (DomainString dstr : dstrings) {
            if (dstr == null)
                continue;
            if (result == null)
                result = dstr;
            else
                result = result.concat(dstr);
        }
        return result;
    }

    public static DomainString join(DomainString... dstrings) {
        DomainString result = null;
        for (DomainString dstr : dstrings) {
            if (dstr == null)
                continue;
            if (result == null)
                result = dstr;
            else
                result = result.join(dstr);
        }
        return result;
    }

}
