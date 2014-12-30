package net.bodz.bas.potato.ref;

import net.bodz.bas.ui.dom1.UiValue;

public class UiHelper {

    public static UiPropertyRefMap explode(Object obj) {
        return explode(UiValue.wrap(obj));
    }

    public static UiPropertyRefMap explode(IRefEntry<?> objRef) {
        UiPropertyRefMap refMap = new UiPropertyRefMap(objRef, false);
        refMap.importProperties();
        return refMap;
    }

}
