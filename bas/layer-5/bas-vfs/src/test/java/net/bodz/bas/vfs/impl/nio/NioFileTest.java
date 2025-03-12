package net.bodz.bas.vfs.impl.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenPomDir;

public class NioFileTest
        extends Assert {

    public static void main(String[] args)
            throws Exception {
        MavenPomDir pomDir = MavenPomDir.fromClass(NioFileTest.class);
        if (pomDir == null)
            throw new NullPointerException("pomDir");

        Path baseDir = pomDir.getBaseDir();
        Path pomXml = baseDir.resolve("pom.xml");

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
