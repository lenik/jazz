package net.bodz.mda.xjdoc.contrib.maven;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.mda.xjdoc.meta.ITagBook;
import net.bodz.mda.xjdoc.meta.JavadocTagBook;

public class PredefinedBooks {

    static Map<String, ITagBook> map;

    static {
        map = new HashMap<String, ITagBook>();
        map.put("javadoc", new JavadocTagBook());
    }

    public static ITagBook getBook(String name) {
        return map.get(name);
    }

    public static void register(String name, ITagBook book) {
        map.put(name, book);
    }

    public static String indexOf(ITagBook book) {
        for (Entry<String, ITagBook> entry : map.entrySet()) {
            if (entry.getValue() == book)
                return entry.getKey();
        }
        return null;
    }

}
