package lenik.lab.m2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.Test;

public class ProjectInfoMojoTest
        extends AbstractMojoTestCase {

    @Test
    public void testExecute()
            throws Exception {
        File testPom = new File(getBasedir(), "src/test/resources/unit/dumpff/test1.xml");
        ProjectInfoMojo mojo = (ProjectInfoMojo) lookupMojo("dump", testPom);
        mojo.execute();
    }

    public static void main(String[] args)
            throws Exception {
        new ProjectInfoMojoTest().itSimple();
    }

    void itSimple()
            throws Exception {
        File testDir = ResourceExtractor.simpleExtractResources(getClass(), "/it/simple1");

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
