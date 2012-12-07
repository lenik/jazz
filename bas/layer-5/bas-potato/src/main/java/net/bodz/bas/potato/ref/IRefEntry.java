package net.bodz.bas.potato.ref;

import net.bodz.bas.lang.ref.Ref;

public interface IRefEntry<T>
        extends Ref<T>, IValueChangeSource {

    IRefDescriptor getDescriptor();

    String getName();

    // void setName(String name);

}
