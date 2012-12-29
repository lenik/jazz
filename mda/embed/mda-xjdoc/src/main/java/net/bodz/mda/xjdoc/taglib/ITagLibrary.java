package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.mda.xjdoc.tagtype.ITagType;

@IndexedType
public interface ITagLibrary {

    ITagType getTagType(String tagName);

}
