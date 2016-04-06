package net.bodz.bas.c.java.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.ctx.sys.UserDirScr;

public class WildcardsExpanderTest
        extends Assert {

    @Test
    public void testMatcher()
            throws IOException {
        Path tmpWorkDirPath = Files.createTempDirectory("wildtest");
        File tmpWorkDir = tmpWorkDirPath.toFile();

        UserDirScr.getInstance().set(tmpWorkDir);

        touch(tmpWorkDir, "a.log");
        touch(tmpWorkDir, "b.txt");
        touch(tmpWorkDir, "dir1/c.log");
        touch(tmpWorkDir, "dir2/sub/d.log");
        touch(tmpWorkDir, "dir2/e.inf");

        WildcardsExpander expander = new WildcardsExpander(tmpWorkDir.toString(), "**.log");
        List<String> foundList = expander.list();
        assertTrue(foundList.contains("a.log"));
        assertTrue(foundList.contains("dir1/c.log"));
        assertTrue(foundList.contains("dir2/sub/d.log"));
        assertFalse(foundList.contains("b.txt"));
    }

    static void touch(File parent, String fileName)
            throws IOException {
        File file = new File(parent, fileName);
        file.getParentFile().mkdirs();
        FileData.writeString(file, "test");
    }

}
