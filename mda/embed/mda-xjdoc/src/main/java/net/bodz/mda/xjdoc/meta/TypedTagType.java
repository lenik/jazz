package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.TypeNameContext;

class TypedTagType
        extends KeyedTagType {

    final TypeNameContext typeNameContext;

    public TypedTagType(ITagType valueTagType, TypeNameContext typeNameContext) {
        super(valueTagType);
        if (typeNameContext == null)
            throw new NullPointerException("typeNameContext");
        this.typeNameContext = typeNameContext;
    }

    @Override
    protected Object parseKey(String keyText) {
        String fqcn = typeNameContext.expand(keyText);
        return fqcn;
    }

    @Override
    protected String formatKey(Object key) {
        String fqcn = (String) key;
        String simpleName = typeNameContext.importTypeName(fqcn);
        return simpleName;
    }

}
