package net.bodz.bas.text.structf;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class StructfInputTest
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
        StructfInput sin = new StructfInput(reader);
        int index = 0;
        int token;
        while ((token = sin.next()) != IStructfInput.EOF) {
            switch (index++) {
            case 0: // header {
                assertEquals(IStructfInput.ELEMENT_BEGIN, token);
                assertEquals("header", sin.getElementName());
                break;
            case 1: // major: 100
                assertEquals(IStructfInput.ATTRIBUTE, token);
                assertEquals("major", sin.getAttributeName());
                assertEquals("100", sin.getAttributeData());
                break;
            case 2: // minor: 200
                assertEquals(IStructfInput.ATTRIBUTE, token);
                assertEquals("minor", sin.getAttributeName());
                assertEquals("200", sin.getAttributeData());
                break;

            case 3: // child c1 {
                assertEquals(IStructfInput.ELEMENT_BEGIN, token);
                assertEquals("child", sin.getElementName());
                assertArrayEquals(new String[] { "c1" }, sin.getElementArguments());
                break;
            case 4: // color: red
                assertEquals(IStructfInput.ATTRIBUTE, token);
                assertEquals("color", sin.getAttributeName());
                assertEquals("red", sin.getAttributeData());
                break;
            case 5: // }
                assertEquals(IStructfInput.ELEMENT_END, token);
                break;

            case 6: // }
                assertEquals(IStructfInput.ELEMENT_END, token);
                break;

            case 7: // chunk RIFF v1 {
                assertEquals(IStructfInput.ELEMENT_BEGIN, token);
                assertEquals("chunk", sin.getElementName());
                break;
            case 8: // description: A multi-line \\ text.
                assertEquals(IStructfInput.ATTRIBUTE, token);
                assertEquals("description", sin.getAttributeName());
                assertEquals("A multi-line text.", sin.getAttributeData());
                break;
            case 9: // name: today
                assertEquals(IStructfInput.ATTRIBUTE, token);
                assertEquals("name", sin.getAttributeName());
                assertEquals("today", sin.getAttributeData());
                break;
            case 10: // }
                assertEquals(IStructfInput.ELEMENT_END, token);
                break;
            case 11:
                assertEquals(IStructfInput.EOF, token);
                break;
            }
        }
        sin.close();
    }

}
