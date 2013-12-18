package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.mda.xjdoc.tagtype.ITagType;

@IndexedType
public interface ITagLibrary {

    String getName();

    String getRootTagName(String tagName);

    /**
     * @return <code>null</code> if the tag name isn't defined.
     */
    ITagType getTagType(String rootTagName);

}
