package net.bodz.mda.xjdoc.model.ff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * classdoc file format.
 * 
 * The .classdoc file should be located along with the .class file:
 * <ul>
 * <li>foo/Bar$Inner.class
 * <li>foo/Bar$Inner.classdoc
 * </ul>
 * 
 * Example:
 * 
 * <pre>
 * #comments
 * %import f.q.c.Name
 * 
 * # optional "[type]"
 * [field:...]
 * [method(int,String[],...)]
 * . = "..." \
 *      en-us "...\n" \
 *          "multi-lines ..." \
 *      zh-cn "..."
 * author = Author 1
 * author = Author 2
 * param.foo = Foo is a parameter.
 * param.bar = Bar is another parameter.
 * throws.IOException = I/O error occurred.
 * </pre>
 * 
 * Features:
 * <ul>
 * <li>'\' in the end-of-line means continuation.
 * <li>\n, \ (space), \", \\ should work.
 * </ul>
 */
public class ClassDocFileParser {

    public static ClassDoc parse(Reader reader)
            throws IOException {
        BufferedReader in;
        if (reader instanceof BufferedReader)
            in = (BufferedReader) reader;
        else
            in = new BufferedReader(reader);

        String line = in.readLine();

        return null;
    }

}
