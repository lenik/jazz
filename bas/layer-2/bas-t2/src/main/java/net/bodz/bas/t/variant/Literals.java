package net.bodz.bas.t.variant;

import java.util.HashSet;
import java.util.Set;

public class Literals {

    public static final Set<String> trueValues = new HashSet<String>();
    public static final Set<String> falseValues = new HashSet<String>();
    static {
        trueValues.add("true");
        trueValues.add("yes");
        trueValues.add("on");
        trueValues.add("1");

        falseValues.add("false");
        falseValues.add("no");
        falseValues.add("off");
        falseValues.add("0");
        falseValues.add("");
    }

}
