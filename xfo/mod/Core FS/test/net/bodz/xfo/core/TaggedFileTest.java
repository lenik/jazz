package net.bodz.xfo.core;

import java.io.File;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;
import net.bodz.xfo.types.AttributedElement;

import org.junit.Test;

public class TaggedFileTest {

    ResLink ixRes;

    public TaggedFileTest() {
        URL classData = Files.classData(getClass(), "tm"); //$NON-NLS-1$
        ixRes = new URLResLink(classData);
    }

    @Test
    public void test1() throws Exception {
        // EclipseProject ep = EclipseProject.findFromCWD();
        TaggedFile ixf = new TaggedFile(new File("/"), ixRes);
        AttributedElement attributes = ixf.getAttributes();
        System.out.println("Attributes: ");
        System.out.println(attributes);
    }

}
