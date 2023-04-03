package net.bodz.uni.shelj.codegen.java.ioforms;

import java.util.ArrayList;

public class StringList
        extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    public void addf(String format, Object... args) {
        String s = String.format(format, args);
        add(s);
    }

}
