package net.bodz.bas.potato.ref;

import net.bodz.bas.lang.ref.Ref;
import net.bodz.bas.potato.traits.IProperty;

public interface IRefEntry<T>
        extends Ref<T> {

    String getName();

    // void setName(String name);

    IProperty getProperty();

}
