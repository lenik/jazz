package net.bodz.bas.c.string;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class StringSearchTest
        extends Assert {

    @Test
    public void testSplitByNoncont() {
        checkSplit("f", "f");
        checkSplit("foo", "foo");
        checkSplit("Foo", "Foo");
        checkSplit("foo-Bar", "fooBar");
        checkSplit("A-Dog", "ADog");
        checkSplit("AB-Dog", "ABDog");
        checkSplit("ABC-Dog", "ABCDog");
        checkSplit("ABC", "ABC");
    }

    void checkSplit(String expected, String s) {
        String[] array = StringSearch.splitByNoncontinuous(s, Character.UPPERCASE_LETTER);
        String actual = StringArray.join("-", array);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        String a = "heyI'amLENIKButIWantYouToKNOW!...That'sTHE123Foo";
        String[] v = StringSearch.splitByNoncontinuous(a, Character.UPPERCASE_LETTER);
        System.out.println(Arrays.asList(v));
        String m = StringId.MIXED.breakCamel(a, "-");
        System.out.println(m);
        String id = StringId.MIXED.toCamel(m);
        System.out.println(id);
    }

}
