package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;

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

    public static DomainString getTextHeader(DomainString text) {
        DomainString header = new XDomainString();
        for (Entry<String, String> entry : text.entrySet()) {
            String domain = entry.getKey();
            String _text = entry.getValue();
            String _header;

            int parbreak = indexOfParbreak(_text);
            if (parbreak == -1)
                _header = _text;
            else
                _header = _text.substring(0, parbreak);

            header.put(domain, _header.trim());
        }
        return header;
    }

    public static DomainString getTextBody(DomainString text) {
        DomainString body = new XDomainString();
        for (Entry<String, String> entry : text.entrySet()) {
            String domain = entry.getKey();
            String _text = entry.getValue();
            String _body;

            int parbreak = indexOfParbreak(_text);
            if (parbreak == -1)
                continue;
            else
                _body = _text.substring(parbreak + 1);

            body.put(domain, _body.trim());
        }
        return body;
    }

    static int indexOfParbreak(String s) {
        int off = 0;
        while ((off = s.indexOf('\n', off)) != -1) {
            int ahead = off + 1;
            if (ahead < s.length()) {
                char ch = s.charAt(ahead);
                if (ch == '\n') // \n\n
                    return off;
                String after = s.substring(off + 1).trim();
                if (!after.isEmpty()) {
                    ch = after.charAt(0);
                    if (ch == '-') // \n- Here '-' is a special char for line-continue.
                        continue;
                    if (!Character.isAlphabetic(ch)) // \n<punct>
                        return off;
                }
            }
        }
        return -1;
    }

}
