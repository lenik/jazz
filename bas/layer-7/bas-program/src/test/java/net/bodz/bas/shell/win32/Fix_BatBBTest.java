package net.bodz.bas.shell.win32;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;
import net.bodz.bas.io.resource.builtin.BytesResource;

public class Fix_BatBBTest {

    @Test
    public void testDoFileEditInputStreamOutputStream()
            throws Throwable {
        Fix_BatBB program = new Fix_BatBB();

        Fix_BatBB.remPrefix = "::F".getBytes();
        program.blockSize = 10;
        program.fillChar = '*';

        String src = ("" + //
                "123456789/" + //
                ":l/" + //
                ":la/" + //
                ":lab/" + //
                ":labe/" + //
                ":label/" + //
                ":label6/" + //
                " :label7/" + //
                "  :label8/" + //
                "   :label9/" + //
                "   :label10/" + //
                "").replace('/', '\n');
        System.out.println(src);
        System.out.println("------------------------------");

        byte[] inputData = src.getBytes();
        BytesResource target = new BytesResource();
        program.process(new ByteArrayResource(inputData), target);

        String result = target.getBuffer().toString();
        result = result.replace(' ', '_');
        System.out.println(result);
        System.out.println("------------------------------");
        System.out.println("Block: ");

        result = result.replace('\n', '/');
        String[] oblks = StringArray.splitBySize(result, program.blockSize);
        for (String oblk : oblks) {
            System.out.println(oblk);
        }

        assertEquals(
                "123456789/:l/:la/::F/:lab/::F/:labe/::F/:label/::F/:label6/::F/_:label7/::F****/__:label8/::F******/___:label9/::F*****/___:label10/",
                result);
    }

}
