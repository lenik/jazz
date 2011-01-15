package net.bodz.bas.meta.info;

import org.junit.Assert;
import org.junit.Test;

public class AuthorUtilTest
        extends Assert {

    @Author({ "U1", "U2" })
    static class ClassA {

    }

    static class ClassA1
            extends ClassA {

    }

    @Author({ "U3", "U2" })
    static class ClassA1i
            extends ClassA1 {

    }

    @Test
    public void testGetAuthors_NotDeclared() {
        String[] authors = AuthorUtil.getAuthors(String.class);
        assertNotNull(authors);
        assertEquals(0, authors.length);
    }

    @Test
    public void testGetAuthors_Declared() {
        String[] authors = AuthorUtil.getAuthors(ClassA1.class);
        String[] expected = { "U1", "U2" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetAuthors_Inherited() {
        String[] authors = AuthorUtil.getAuthors(ClassA1.class);
        String[] expected = { "U1", "U2" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetClassAuthors_Declared() {
        String[] authors = AuthorUtil.getClassAuthors(ClassA.class, false, false, false);
        String[] expected = { "U1", "U2" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetClassAuthors_AllOccurences() {
        String[] authors = AuthorUtil.getClassAuthors(ClassA1.class, true, false, false);
        String[] expected = { "U1", "U2" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetClassAuthors_AllOccurences2() {
        String[] authors = AuthorUtil.getClassAuthors(ClassA1i.class, true, false, false);
        String[] expected = { "U1", "U2", "U3", "U2" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetClassAuthors_AllOccurences_Unique() {
        String[] authors = AuthorUtil.getClassAuthors(ClassA1i.class, true, false, true);
        String[] expected = { "U1", "U2", "U3" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetClassAuthors_AllOccurences_Reversed() {
        String[] authors = AuthorUtil.getClassAuthors(ClassA1i.class, true, true, false);
        String[] expected = { "U3", "U2", "U1", "U2" };
        assertArrayEquals(expected, authors);
    }

    @Test
    public void testGetClassAuthors_AllOccurences_Reversed_Unique() {
        String[] authors = AuthorUtil.getClassAuthors(ClassA1i.class, true, true, true);
        String[] expected = { "U3", "U2", "U1" };
        assertArrayEquals(expected, authors);
    }

    public static void main(String[] args) {
        Author _author = ClassA1i.class.getAnnotation(Author.class);
        if (_author != null) {
            for (String s : _author.value())
                System.out.println("Author: " + s);
        }
    }

}
