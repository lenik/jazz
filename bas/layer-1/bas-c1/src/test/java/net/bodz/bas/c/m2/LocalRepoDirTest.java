package net.bodz.bas.c.m2;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import static org.junit.Assert.fail;

public class LocalRepoDirTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    public static void main(String[] args) {
        String jar = "/home/lenik/.m2/repository/net/bodz/bas/bas-i18n/2.0.0-SNAPSHOT/bas-i18n-2.0.0-SNAPSHOT-tests.jar";
        Path jarFile = Paths.get(jar);
        LocalRepoDir repoDir = LocalRepoDir.closest(jarFile);
        ArtifactId qName = repoDir.getQualifiedName(jarFile);
        System.out.println(qName);
    }

}
