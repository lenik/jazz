package net.bodz.mda.xjdoc.tags;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface ITagLibrary {

    ITagType getTagType(String tagName);

}
