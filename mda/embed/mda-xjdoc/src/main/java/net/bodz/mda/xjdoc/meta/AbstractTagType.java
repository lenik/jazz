package net.bodz.mda.xjdoc.meta;

import net.bodz.mda.xjdoc.util.IImportMapProvider;

public abstract class AbstractTagType
        implements ITagType {

    public RepeatTagType repeat() {
        return new RepeatTagType(this);
    }

    public KeyedTagType keyed() {
        return new KeyedTagType(this);
    }

    public TypedTagType typed(IImportMapProvider importMapProvider) {
        return new TypedTagType(this, importMapProvider);
    }

}
