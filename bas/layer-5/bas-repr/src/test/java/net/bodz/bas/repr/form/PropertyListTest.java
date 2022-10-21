package net.bodz.bas.repr.form;

import java.util.List;

import org.junit.Assert;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;

import user.book.Author;
import user.book.Book;
import user.book.Org;
import user.book.Page;
import user.book.Store;

public class PropertyListTest
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
        IType bookType = PotatoTypes.getInstance().loadType(Book.class);

        MutableFormDecl formDecl = new FormDeclBuilder().build(bookType);
        List<PropertyChain> list = formDecl.resolvePattern("author.name");

        for (PropertyChain chain : list) {
            Object value = chain.getValue(tutorial);
            System.out.printf("tutorial[%s] => %s.\n", chain.getPath(), value);
        }
    }

}
