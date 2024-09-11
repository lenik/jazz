package net.bodz.mda.xjdoc.model.javadoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import net.bodz.bas.c.string.DelimSeqTokenizer;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.IMutableElementDoc;

public class XjdocModifier {

    public static void process(IMutableElementDoc doc) {
        String docParams = IElementDoc.DESCRIPTION;
        String docParamsOverride = doc.getString(".docpar");
        if (docParamsOverride != null) {
            if (docParamsOverride.isEmpty())
                return;
            docParams = docParamsOverride;
        }

        if (! docParams.contains(" ")) {
            doc.setTag(docParams, doc.getText());
            return;
        }

        List<String> nameVec = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(docParams, " ");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (! token.isEmpty())
                nameVec.add(token);
        }
        int nameCount = nameVec.size();
        assert nameCount > 1;

        // create tag array (buffer)
        iString[] textVec = new iString[nameCount];
        for (int i = 0; i < nameCount; i++)
            textVec[i] = new XiString();

        // convert "en Par1 Par2; fr Par1 Par2 Par3; ..."
        // to text1="en Par1 fr Par1"; text2="en Par2 fr Par2"; ...
        for (Entry<String, String> entry : doc.getText().entrySet()) {
            String lang = entry.getKey();
            String str = entry.getValue();
            if (str == null)
                continue;
            DelimSeqTokenizer pars = new DelimSeqTokenizer(str, '\n', '\n');
            for (int i = 0; i < nameCount && pars.hasNext(); i++) {
                String par = i == nameCount - 1 ? pars.getRemaining() : pars.next();
                textVec[i].put(lang, par);
            }
        }

        // rewrite tags.
        for (int i = 0; i < nameCount; i++) {
            String name = nameVec.get(i);
            if (name.equals("-"))
                continue;
            iString text = textVec[i];
            doc.setTag(name, text);
        }
    }

}
