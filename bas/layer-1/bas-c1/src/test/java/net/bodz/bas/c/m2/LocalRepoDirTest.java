package net.bodz.bas.c.m2;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

public class LocalRepoDirTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    public static void main(String[] args) {
        String jar = "/home/lenik/.m2/repository/net/bodz/bas/bas-i18n/2.0.0-SNAPSHOT/bas-i18n-2.0.0-SNAPSHOT-tests.jar";
        File jarFile = new File(jar);
        LocalRepoDir repoDir = LocalRepoDir.closest(jarFile);
        ArtifactId qName = repoDir.getQualifiedName(jarFile);
        System.out.println(qName);
    }

}
