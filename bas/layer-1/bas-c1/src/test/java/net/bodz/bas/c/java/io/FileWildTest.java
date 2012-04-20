package net.bodz.bas.c.java.io;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FileWildTest
        extends Assert {

    @Test
    public void testListFiles() {

        List<File> find = FileWild.listFiles("K:/workspace/net.bodz/*/mod/*/src/net/bodz");
        if (find == null)
            System.out.println("no match");
        else
            for (File f : find)
                System.out.println(f);
    }

}
