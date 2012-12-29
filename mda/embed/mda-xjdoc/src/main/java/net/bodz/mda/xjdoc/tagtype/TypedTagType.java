package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.rtx.INegotiation;
import net.bodz.mda.xjdoc.util.ImportMap;

public class TypedTagType
        extends KeyedTagType {

    public TypedTagType(ITagType valueTagType) {
        super(valueTagType);
    }

    @Override
    protected Object parseKey(String keyText, INegotiation negotiation) {
        ImportMap importMap = ImportMap.getInstance(negotiation);
        String fqcn = importMap.normalize(keyText);
        return fqcn;
    }

    @Override
    protected String formatKey(Object key, INegotiation negotiation) {
        ImportMap importMap = ImportMap.getInstance(negotiation);
        String fqcn = (String) key;
        String simpleName = importMap.add(fqcn);
        return simpleName;
    }

}
