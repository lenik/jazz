package net.bodz.bas.c.string;

import java.util.Arrays;

import org.junit.Assert;

public class StringSearchTest
        extends Assert {

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
