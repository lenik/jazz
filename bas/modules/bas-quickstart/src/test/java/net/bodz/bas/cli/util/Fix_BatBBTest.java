package net.bodz.bas.cli.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class Fix_BatBBTest {

    @Test
    public void testDoFileEditInputStreamOutputStream() throws Throwable {
        Fix_BatBB app = new Fix_BatBB();
        Fix_BatBB.remPrefix = "::F".getBytes(); //$NON-NLS-1$
        app.blockSize = 10;
        app.fillChar = '*';

        String src = ("" + //  //$NON-NLS-1$
                "123456789/" + // //$NON-NLS-1$
                ":l/" + // //$NON-NLS-1$
                ":la/" + // //$NON-NLS-1$
                ":lab/" + // //$NON-NLS-1$
                ":labe/" + // //$NON-NLS-1$
                ":label/" + // //$NON-NLS-1$
                ":label6/" + // //$NON-NLS-1$
                " :label7/" + // //$NON-NLS-1$
                "  :label8/" + // //$NON-NLS-1$
                "   :label9/" + // //$NON-NLS-1$
                "   :label10/" + // //$NON-NLS-1$
                "").replace('/', '\n'); //$NON-NLS-1$
        System.out.println(src);
        System.out.println("------------------------------"); //$NON-NLS-1$
        ByteArrayInputStream in = new ByteArrayInputStream(src.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        app.doEditByIO(in, out);
        String dst = new String(out.toByteArray());
        dst = dst.replace(' ', '_');
        System.out.println(dst);
        System.out.println("------------------------------"); //$NON-NLS-1$
        System.out.println("Block: "); //$NON-NLS-1$
        dst = dst.replace('\n', '/');
        String[] oblks = Strings.splitBySize(dst, app.blockSize);
        for (String oblk : oblks) {
            System.out.println(oblk);
        }

        assertEquals(
                "123456789/:l/:la/::F/:lab/::F/:labe/::F/:label/::F/:label6/::F/_:label7/::F****/__:label8/::F******/___:label9/::F*****/___:label10/", //$NON-NLS-1$
                dst);
    }

}
