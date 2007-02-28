package net.bodz.xml.xmod.util;

import java.util.HashMap;
import java.util.Map;

public class Docobjs {

    private static Map<String, Docobj> vars;

    static {
        vars = new HashMap<String, Docobj>();
    }

    public static Docobj get(String id) {
        return vars.get(id);
    }

    public static void set(String id, Docobj docobj) {
        vars.put(id, docobj);
    }

    public static boolean unset(String id) {
        return vars.remove(id) != null;
    }

}
