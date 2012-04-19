package net.bodz.bas.c.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringGrep {

    public static String[] findAll(String s, Pattern pattern, int group) {
        Matcher m = pattern.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(group));
        }
        return list.toArray(new String[0]);
    }

    public static String[] findAll(String s, Pattern pattern) {
        return findAll(s, pattern, 0);
    }

    public static String[] findAll(String s, Pattern pattern, String replacement) {
        Matcher m = pattern.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            String deref = IndexVarSubst.subst(replacement, m);
            list.add(deref);
        }
        return list.toArray(new String[0]);
    }

}
