package net.bodz.mda.xjdoc.model.javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.bodz.bas.c.string.BrokenCharsTokenizer;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.IMutableElementDoc;
import net.bodz.mda.xjdoc.tagtype.TextTag;

public class XjdocModifier {

    public static void process(IMutableElementDoc doc) {
        String docparTagName = IElementDoc.DESCRIPTION;
        String docparTagNameOverride = doc.getString(".docpar");
        if (docparTagNameOverride != null) {
            if (docparTagNameOverride.isEmpty())
                return;
            docparTagName = docparTagNameOverride;
        }

        if (! docparTagName.contains(" ")) {
            TextTag docparTag = doc.makeTag(docparTagName);
            docparTag.setData(doc.getText());
            return;
        }

        List<String> tags = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(docparTagName, " ");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (! token.isEmpty())
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
            for (int i = 0; i < ntag && pars.hasNext(); i++) {
                String s = i == ntag - 1 ? pars.getRemaining() : pars.next();
                vals[i].put(lang, s);
            }
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
