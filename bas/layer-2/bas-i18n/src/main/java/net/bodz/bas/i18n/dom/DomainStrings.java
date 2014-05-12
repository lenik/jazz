package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;

import net.bodz.bas.c.string.BrokenCharsTokenizer;

public class DomainStrings {

    public static iString concat(iString... dstrings) {
        iString result = null;
        for (iString dstr : dstrings) {
            if (dstr == null)
                continue;
            if (result == null)
                result = dstr;
            else
                result = result.concat(dstr);
        }
        return result;
    }

    public static iString join(iString... dstrings) {
        iString result = null;
        for (iString dstr : dstrings) {
            if (dstr == null)
                continue;
            if (result == null)
                result = dstr;
            else
                result = result.join(dstr);
        }
        return result;
    }

    public static iString headPar(iString text) {
        iString par = new XiString();
        for (Entry<String, String> entry : text.entrySet()) {
            String domain = entry.getKey();
            String str = entry.getValue();
            if (str == null)
                continue;

            BrokenCharsTokenizer pars = new BrokenCharsTokenizer(str, '\n', '\n');
            String head = pars.next();

            par.put(domain, head.trim());
        }
        return par;
    }

    public static iString tailPar(iString text) {
        iString par = new XiString();
        for (Entry<String, String> entry : text.entrySet()) {
            String domain = entry.getKey();
            String str = entry.getValue();
            if (str == null)
                continue;

            BrokenCharsTokenizer pars = new BrokenCharsTokenizer(str, '\n', '\n');
            pars.next();
            String tail = pars.hasNext() ? pars.next() : "";

            par.put(domain, tail.trim());
        }
        return par;
    }

}
