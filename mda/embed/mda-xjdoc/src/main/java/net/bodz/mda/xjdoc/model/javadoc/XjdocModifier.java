package net.bodz.mda.xjdoc.model.javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.bodz.bas.c.string.BrokenCharsTokenizer;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class XjdocModifier {

    public static void process(IElementDoc doc) {
        String docpar = "description";
        Object _docpar = doc.getTag(".docpar");
        if (_docpar != null) {
            docpar = _docpar.toString().trim();
            if (docpar.isEmpty())
                return;
        }

        if (!docpar.contains(" ")) {
            doc.setTag(docpar, doc.getText());
            return;
        }

        List<String> tags = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(docpar, " ");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (!token.isEmpty())
                tags.add(token);
        }
        int ntag = tags.size();
        assert ntag > 1;

        // create tag array (buffer)
        iString[] vals = new iString[ntag];
        for (int i = 0; i < ntag; i++)
            vals[i] = new XiString();

        // split text => tags
        for (Entry<String, String> entry : doc.getText().entrySet()) {
            String lang = entry.getKey();
            String str = entry.getValue();
            if (str == null)
                continue;
            BrokenCharsTokenizer pars = new BrokenCharsTokenizer(str, '\n', '\n');
            for (int i = 0; i < ntag && pars.hasNext(); i++)
                vals[i].put(lang, i == ntag - 1 ? pars.getRemaining() : pars.next());
        }

        // rewrite tags.
        for (int i = 0; i < ntag; i++) {
            String tag = tags.get(i);
            if (tag.equals("-"))
                continue;
            doc.setTag(tags.get(i), vals[i]);
        }
    }

}
