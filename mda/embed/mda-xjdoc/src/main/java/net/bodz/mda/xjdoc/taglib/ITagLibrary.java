package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.mda.xjdoc.model.IDocTag;

@IndexedType
public interface ITagLibrary {

    String getName();

    String getRootTagName(String tagName);

    /**
     * @return <code>null</code> if the tag name isn't defined.
     */
    IDocTag<?> createTag(String rootTagName);

    ITagLibrary NULL = NullTagLibrary.INSTANCE;

}
