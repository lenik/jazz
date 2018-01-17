package net.bodz.bas.fmt.textmap;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom.AbstractiStringTypers;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.MutableElementDoc;

public class I18nTextMapDocLoader {

    public static IElementDoc load(IStreamInputSource inputSource)
            throws ParseException, IOException {
        int format = AbstractiStringTypers.PARA_LANG;

        MutableElementDoc doc = new MutableElementDoc(Xjdocs.getDefaultTagLibrary());

        for (Entry<String, iString> ent : I18nTextMapParser.parse(inputSource, format)) {
            String key = ent.getKey();
            iString value = ent.getValue();
            switch (key) {
            case "text":
                doc.setText(value);
                break;
            default:
                doc.setTag(key, value);
            }
        }

        return doc;
    }

}
