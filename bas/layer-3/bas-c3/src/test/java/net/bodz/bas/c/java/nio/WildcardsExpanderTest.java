package net.bodz.bas.c.java.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.sys.UserDirVars;

public class WildcardsExpanderTest
        extends Assert {

    @Test
    public void testMatcher()
            throws IOException {
        Path tmpWorkDir = Files.createTempDirectory("wildtest");

        UserDirVars.getInstance().set(tmpWorkDir);

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

    static void touch(Path parent, String fileName)
            throws IOException {
        Path file = parent.resolve(fileName);
        Files.createDirectories(file.getParent());
        FileData.writeString(file, "test");
    }

}
