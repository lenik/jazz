package net.bodz.bas.repr.form;

import org.junit.Assert;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;

import user.book.Author;
import user.book.Book;
import user.book.Org;
import user.book.Page;
import user.book.Store;

public class PathFieldListTest
        extends Assert {

    static Author lenik = new Author("lenik");
    static Org bodz = new Org("bodz");
    static Store express = new Store("express");
    static Book tutorial = new Book("tutorial");
    static Page hello = new Page("hello");

    static {
        bodz.getAuthors().add(lenik);
        tutorial.setAuthor(lenik);
        express.getBooks().add(tutorial);
        tutorial.getPages().add(hello);
        express.getBooks().add(tutorial);
    }

    public static void main(String[] args)
            throws Exception {
        IType bookType = PotatoTypes.getInstance().forClass(Book.class);

        PathFieldList fields = new PathFieldList();
        MutableFormDecl formDecl = new FormDeclBuilder().build(bookType);
        fields.parseAndAdd(formDecl, "author.name");

        for (PathField pf : fields) {
            Object value = pf.getValue(tutorial);
            System.out.printf("tutorial[%s] => %s.\n", pf.getPath(), value);
        }
    }

}
