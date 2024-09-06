package net.bodz.mda.xjdoc.model;

import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public class MutableElementDoc
        extends AbstractElementDoc {

    String name;

    public MutableElementDoc(ITagLibrary tagLibrary) {
        super(tagLibrary);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(IDocVisitor visitor) {
        visitor.mutable(this);
    }

}
