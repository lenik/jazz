package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.TypeNameContext;

public abstract class AbstractTagType
        implements ITagType {

    public RepeatTagType repeat() {
        return new RepeatTagType(this);
    }

    public KeyedTagType keyed() {
        return new KeyedTagType(this);
    }

    public TypedTagType typed(TypeNameContext typeNameContext) {
        return new TypedTagType(this, typeNameContext);
    }

}
