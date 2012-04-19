package net.bodz.bas.enh.io;

import java.io.File;
import java.util.List;

import net.bodz.bas.c.java.io.FileWild;

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
