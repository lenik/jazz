package net.bodz.bas.c1.annotations.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import net.bodz.bas.c1.annotations.Tags;

import org.junit.Test;

public class TagsUtilTest {

    @Tags({ "Tag1", "Tag2" })
    static class ClassA {

    }

    static class ClassA1
            extends ClassA {

    }

    @Tags({ "Tag3", "Tag2" })
    static class ClassA1i
            extends ClassA1 {

    }

    @Test
    public void testGetTags_NotDeclared() {
        String[] tags = TagsUtil.getTags(String.class);
        assertNotNull(tags);
        assertEquals(0, tags.length);
    }

    @Test
    public void testGetTags_Declared() {
        String[] tags = TagsUtil.getTags(ClassA1.class);
        String[] expected = { "Tag1", "Tag2" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetTags_Inherited() {
        String[] tags = TagsUtil.getTags(ClassA1.class);
        String[] expected = { "Tag1", "Tag2" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetClassTags_Declared() {
        String[] tags = TagsUtil.getClassTags(ClassA.class, false, false, false);
        String[] expected = { "Tag1", "Tag2" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetClassTags_AllOccurences() {
        String[] tags = TagsUtil.getClassTags(ClassA1.class, true, false, false);
        String[] expected = { "Tag1", "Tag2" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetClassTags_AllOccurences2() {
        String[] tags = TagsUtil.getClassTags(ClassA1i.class, true, false, false);
        String[] expected = { "Tag1", "Tag2", "Tag3", "Tag2" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetClassTags_AllOccurences_Unique() {
        String[] tags = TagsUtil.getClassTags(ClassA1i.class, true, false, true);
        String[] expected = { "Tag1", "Tag2", "Tag3" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetClassTags_AllOccurences_Reversed() {
        String[] tags = TagsUtil.getClassTags(ClassA1i.class, true, true, false);
        String[] expected = { "Tag3", "Tag2", "Tag1", "Tag2" };
        assertArrayEquals(expected, tags);
    }

    @Test
    public void testGetClassTags_AllOccurences_Reversed_Unique() {
        String[] tags = TagsUtil.getClassTags(ClassA1i.class, true, true, true);
        String[] expected = { "Tag3", "Tag2", "Tag1" };
        assertArrayEquals(expected, tags);
    }

}
