package net.bodz.bas.files;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class FileFindTest
        extends TestCase {

    @Test
    public void testFind1() {
        List<File> find = FileFind.find("K:/workspace/net.bodz/*/mod/*/src/net/bodz");
        if (find == null)
            System.out.println("no match");
        else
            for (File f : find)
                System.out.println(f);
    }

}
