package net.bodz.bas.c.java.nio;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.system.SystemColos;

public class WildcardsExpanderTest
        extends Assert
        implements SystemColos {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    public static void main(String[] args) {
        workdir.set(new File("/tmp"));
        WildcardsExpander expander = new WildcardsExpander("**.log");
        for (String s : expander) {
            System.out.println("> " + s + " <");
        }
    }

}
