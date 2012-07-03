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

    public static void register(String name, ITagBook book) {
        map.put(name, book);
    }

    public static ITagBook resolve(String name) {
        ITagBook book = map.get(name);
        if (book == null)
            try {
                Class<? extends ITagBook> bookClass = (Class<? extends ITagBook>) Class.forName(name);
                book = bookClass.newInstance();
            } catch (ClassNotFoundException e) {
                return null;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to instantiate book: " + name, e);
            }
        return book;
    }

    public static String getName(ITagBook book) {
        if (book == null)
            throw new NullPointerException("book");
        for (Entry<String, ITagBook> entry : map.entrySet()) {
            if (entry.getValue() == book)
                return entry.getKey();
        }
        return book.getClass().getName();
    }

}
