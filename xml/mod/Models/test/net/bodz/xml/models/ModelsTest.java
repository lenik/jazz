package net.bodz.xml.models;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.bodz.bas.snm.SJProject;

import org.junit.Test;

public class ModelsTest {

    @Test
    public void testFind() throws Exception {
        File projectDir = SJProject.findProjectBase(getClass());

        File pdbHome = Models.findModelHome("pdb");
        assertEquals(new File(projectDir, "models/p/pdb"), pdbHome);

        File nfsLibraryHome = Models.findModelHome("nfs/library");
        assertEquals(new File(projectDir, "models/n/nfs/library"), nfsLibraryHome);
    }

}
