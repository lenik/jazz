package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.util.ImportMap;

public class FirstWordFQCNTagType
        extends FirstWordKeyTagType {

    public FirstWordFQCNTagType(ITagType valueTagType) {
        super(valueTagType);
    }

    @Override
    protected Object parseKey(String keyText, IOptions options) {
        ImportMap importMap = ImportMap.getInstance(options);
        String fqcn = importMap.normalize(keyText);
        return fqcn;
    }

    @Override
    protected String formatKey(Object key, IOptions options) {
        ImportMap importMap = ImportMap.getInstance(options);
        String fqcn = (String) key;
        String simpleName = importMap.add(fqcn);
        return simpleName;
    }

}
