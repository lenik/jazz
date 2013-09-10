package net.bodz.bas.text.rst;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.text.rst.IRstInput;
import net.bodz.bas.text.rst.RstInput;

public class RstInputTest
        extends Assert {

    String script = "header {\n" + //
            "    major: 100\n" + //
            "    minor: 200\n" + //
            "    child c1 {\n" + //
            "        color: red\n" + //
            "    }\n" + //
            "}\n" + //
            "chunk RIFF v1 {\n" + //
            "    description: A multi-line \\\n" + //
            "text.\n" + //
            "    name: today\n" + //
            "}";

    @Test
    public void test1()
            throws IOException {
        StringReader reader = new StringReader(script);
        RstInput sin = new RstInput(reader);
        int index = 0;
        int token;
        while ((token = sin.next()) != IRstInput.EOF) {
            switch (index++) {
            case 0: // header {
                assertEquals(IRstInput.ELEMENT_BEGIN, token);
                assertEquals("header", sin.getElementName());
                break;
            case 1: // major: 100
                assertEquals(IRstInput.ATTRIBUTE, token);
                assertEquals("major", sin.getAttributeName());
                assertEquals("100", sin.getAttributeData());
                break;
            case 2: // minor: 200
                assertEquals(IRstInput.ATTRIBUTE, token);
                assertEquals("minor", sin.getAttributeName());
                assertEquals("200", sin.getAttributeData());
                break;

            case 3: // child c1 {
                assertEquals(IRstInput.ELEMENT_BEGIN, token);
                assertEquals("child", sin.getElementName());
                assertArrayEquals(new String[] { "c1" }, sin.getElementArguments());
                break;
            case 4: // color: red
                assertEquals(IRstInput.ATTRIBUTE, token);
                assertEquals("color", sin.getAttributeName());
                assertEquals("red", sin.getAttributeData());
                break;
            case 5: // }
                assertEquals(IRstInput.ELEMENT_END, token);
                break;

            case 6: // }
                assertEquals(IRstInput.ELEMENT_END, token);
                break;

            case 7: // chunk RIFF v1 {
                assertEquals(IRstInput.ELEMENT_BEGIN, token);
                assertEquals("chunk", sin.getElementName());
                break;
            case 8: // description: A multi-line \\ text.
                assertEquals(IRstInput.ATTRIBUTE, token);
                assertEquals("description", sin.getAttributeName());
                assertEquals("A multi-line text.", sin.getAttributeData());
                break;
            case 9: // name: today
                assertEquals(IRstInput.ATTRIBUTE, token);
                assertEquals("name", sin.getAttributeName());
                assertEquals("today", sin.getAttributeData());
                break;
            case 10: // }
                assertEquals(IRstInput.ELEMENT_END, token);
                break;
            case 11:
                assertEquals(IRstInput.EOF, token);
                break;
            }
        }
        sin.close();
    }

}
