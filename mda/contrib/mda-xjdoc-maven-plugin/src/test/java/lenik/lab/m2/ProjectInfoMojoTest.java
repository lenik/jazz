package lenik.lab.m2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.junit.Test;

import net.bodz.bas.m2.test.MyMojoTestCase;

public class ProjectInfoMojoTest
        extends MyMojoTestCase {

    @Test
    public void testExecute()
            throws Exception {
        File testPom = getResourceFile("dump/pom.xml");
        assertTrue(testPom.exists());

        ProjectInfoMojo mojo = (ProjectInfoMojo) lookupMojo("dump", testPom);
        mojo.execute();
    }

    public static void main(String[] args)
            throws Exception {
        File testDir = ResourceExtractor.simpleExtractResources(ProjectInfoMojoTest.class, "/lenik/lab/m2/dump");
        assertTrue(testDir.isDirectory());

        Verifier verifier = new Verifier(testDir.getAbsolutePath());
        // verifier.deleteArtifact("", name, version, ext)

        List<String> cliOptions = verifier.getCliOptions();
        cliOptions = new ArrayList<String>();
        cliOptions.add("-N");
        verifier.setCliOptions(cliOptions);

        verifier.executeGoal("package");

        verifier.verifyErrorFreeLog();

        verifier.resetStreams();
    }

}
