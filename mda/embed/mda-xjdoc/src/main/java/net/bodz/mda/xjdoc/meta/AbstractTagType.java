package net.bodz.mda.xjdoc.meta;

public abstract class AbstractTagType
        implements ITagType {

    public RepeatTagType repeat() {
        return new RepeatTagType(this);
    }

    public KeyedTagType keyed() {
        return new KeyedTagType(this);
    }

    public TypedTagType typed() {
        return new TypedTagType(this);
    }

}
