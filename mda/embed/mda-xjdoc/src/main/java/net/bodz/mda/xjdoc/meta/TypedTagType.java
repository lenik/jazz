package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.IImportMapProvider;
import net.bodz.mda.xjdoc.util.ImportMap;

public class TypedTagType
        extends KeyedTagType {

    final IImportMapProvider importMapProvider;

    public TypedTagType(ITagType valueTagType, IImportMapProvider importMapProvider) {
        super(valueTagType);
        if (importMapProvider == null)
            throw new NullPointerException("importMapProvider");
        this.importMapProvider = importMapProvider;
    }

    @Override
    protected Object parseKey(String keyText) {
        ImportMap importMap = importMapProvider.getImportMap();
        String fqcn = importMap.normalize(keyText);
        return fqcn;
    }

    @Override
    protected String formatKey(Object key) {
        ImportMap importMap = importMapProvider.getImportMap();
        String fqcn = (String) key;
        String simpleName = importMap.add(fqcn);
        return simpleName;
    }

}
