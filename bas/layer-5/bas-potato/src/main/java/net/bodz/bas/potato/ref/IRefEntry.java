package net.bodz.bas.potato.ref;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.t.ref.Ref;

public interface IRefEntry<T>
        extends Ref<T>, IElement, IValueChangeSource {

    boolean isValueChangeSource();

}
