package net.bodz.mda.xjdoc.tags;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class TagBooks {

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

    public static MergedTagBook parse(String bookNames) {
        if (bookNames == null)
            throw new NullPointerException("bookNames");
        MergedTagBook mergedBook = new MergedTagBook();
        for (String bookName : bookNames.split(",")) {
            bookName = bookName.trim();
            if (bookName.isEmpty())
                continue;
            ITagBook book = TagBooks.resolve(bookName);
            if (book == null)
                throw new IllegalArgumentException("Bad book name: " + bookName);
            mergedBook.add(book);
        }
        return mergedBook;
    }

}
