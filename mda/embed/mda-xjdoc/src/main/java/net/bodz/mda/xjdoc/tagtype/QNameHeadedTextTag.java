package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.util.ImportMap;

public class QNameHeadedTextTag
        extends HeadedTextTag {

    @Override
    protected String name(String qName, IOptions options) {
        ImportMap importMap = options.require(ImportMap.class);
        String simpleName = importMap.add(qName);
        return simpleName;
    }

    @Override
    protected String firstWord(String name, IOptions options) {
        ImportMap importMap = options.require(ImportMap.class);
        String qName = importMap.normalize(name);
        return qName;
    }

}
