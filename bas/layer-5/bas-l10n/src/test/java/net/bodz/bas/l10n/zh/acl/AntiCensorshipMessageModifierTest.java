package net.bodz.bas.l10n.zh.acl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AntiCensorshipMessageModifierTest
        extends Assert {

    AntiCensorshipMessageModifier modifier = AntiCensorshipMessageModifier.INSTANCE;

    @Test
    public void nomatch() {
        String output = modifier.apply("hello, world");
        assertEquals("hello, world", output);
    }

    @Test
    public void match1() {
        List<String> list = Arrays.asList("TG");
        String output = modifier.apply("共产党");
        assertTrue(list.contains(output));
    }

    @Test
    public void match2() {
        String output = modifier.apply("共产共产党共共产党");
        System.out.println(output);
    }

    public static void main(String[] args) {
        AntiCensorshipMessageModifier mod = AntiCensorshipMessageModifier.INSTANCE;

    }

}
