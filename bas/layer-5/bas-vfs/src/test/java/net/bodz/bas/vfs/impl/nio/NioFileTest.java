package net.bodz.bas.vfs.impl.nio;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenProjectOrigin;

public class NioFileTest
        extends Assert {

    public static void main(String[] args)
            throws Exception {
        MavenProjectOrigin mpo = MavenProjectOrigin.fromClass(NioFileTest.class);
        File projectDirFile = mpo.getProjectDir();
        Path projectDir = Paths.get(projectDirFile.getPath());

        Path pomXml = projectDir.resolve("pom.xml");

        SeekableByteChannel ch = Files.newByteChannel(pomXml);

        ByteBuffer buf = ByteBuffer.allocate(30);
        byte[] array = buf.array();

        while (ch.read(buf) != -1) {
            int position = buf.position();
            buf.clear();
            String string = new String(array, 0, position);
            System.out.println("[" + string + "]");
        }

        ch.close();
    }

}
