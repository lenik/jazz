package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.text.locale.NLSDict;
import net.bodz.bas.types.TreePath;

public class CombinedBook extends _Book {

    private List<Book> books;

    public CombinedBook(Book... books) {
        this.books = new ArrayList<Book>(books.length);
        for (Book map : books)
            this.books.add(map);
    }

    @Override
    public TreePath getFirst() {
        if (books.isEmpty())
            return null;
        return books.get(0).getFirst();
    }

    @Override
    public boolean contains(TreePath path) {
        for (Book book : books)
            if (book.contains(path))
                return true;
        return false;
    }

    @Override
    public Page getPage(TreePath path) {
        for (Book book : books) {
            Page page = book.getPage(path);
            if (page != null)
                return page;
        }
        return null;
    }

    public int size() {
        return books.size();
    }

    public void add(int index, Book element) {
        books.add(index, element);
    }

    public boolean add(Book e) {
        return books.add(e);
    }

    @Override
    public List<PageMethod> getMethods() {
        /** XXX - better to using concat-list */
        List<PageMethod> methods = new ArrayList<PageMethod>();
        for (Book book : books) {
            Collection<PageMethod> mv = book.getMethods();
            if (mv != null)
                methods.addAll(mv);
        }
        return Collections.unmodifiableList(methods);
    }

    @Override
    public NLSDict getDict() {
        // XXX - merge dicts
        for (Book book : books) {
            NLSDict dict = book.getDict();
            if (dict != null)
                return dict;
        }
        return null;
    }

}
