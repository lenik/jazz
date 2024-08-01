package net.bodz.bas.esm.util;

import java.util.HashSet;
import java.util.Set;

public class TsCodeStyle {

    Set<String> newLineProps = new HashSet<>();

    public TsCodeStyle() {
    }

    public boolean isNewLineProp(String propertyName) {
        return newLineProps.contains(propertyName);
    }

    public static TsCodeStyle DEFAULT = new TsCodeStyle();
    public static TsCodeStyle LOOSE = new TsCodeStyle();

    static {
        LOOSE.newLineProps.add("description");
    }

}
