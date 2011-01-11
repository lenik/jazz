package net.bodz.bas.meta.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.AnnotatedElement;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.annotation.util.AnnotationUtil;
import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.exceptions.UnexpectedException;

/**
 * @test DocUtilTest
 */
public class DocUtil {

    public static String getDoc(AnnotatedElement annotatedElement) {
        if (annotatedElement == null)
            throw new NullPointerException("annotatedElement");
        Doc _doc = annotatedElement.getAnnotation(Doc.class);
        if (_doc == null)
            return null;
        String docRaw = _doc.value();
        try {
            return parseDoc(docRaw, null);
        } catch (ParseException e) {
            throw new UnexpectedException(e);
        }
    }

    public static String getClassDoc(Class<?> clazz)
            throws ParseException {
        return getClassDoc(clazz, true, false, "\n\n");
    }

    public static String getClassDoc(Class<?> clazz, boolean mergeAllOccurences, boolean reverseOrder,
            String pageSeparator)
            throws ParseException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (!mergeAllOccurences) {
            Doc _doc = clazz.getAnnotation(Doc.class);
            if (_doc == null)
                return null;
            String docRaw = _doc.value();
            return parseDoc(docRaw, clazz);
        } else {
            List<String> pages = new ArrayList<String>();
            int cc = 0;
            while (clazz != null) {
                Doc _doc = AnnotationUtil.getDeclaredAnnotation(clazz, Doc.class);
                if (_doc != null) {
                    String docRaw = _doc.value();
                    assert docRaw != null;
                    String doc = parseDoc(docRaw, clazz);
                    pages.add(doc);
                    cc += doc.length() + 10;
                }
                clazz = clazz.getSuperclass();
            }
            if (!reverseOrder)
                Collections.reverse(pages);
            StringBuffer buf = new StringBuffer(cc);
            for (String page : pages) {
                if (buf.length() != 0)
                    buf.append(pageSeparator);
                buf.append(page);
            }
            return buf.toString();
        }
    }

    public static String parseDoc(String doc, Class<?> declaredClass)
            throws ParseException {
        if (doc.startsWith("#")) {
            // #bundle.property
            throw new NotImplementedException();
        } else if (doc.startsWith("$")) {
            // Class resource url:
            String classResource = doc.substring(1);
            URL resource = declaredClass.getResource(classResource);
            if (resource == null)
                throw new ParseException("Bad class resource path: " + classResource);
            StringBuffer contents = new StringBuffer();
            int lineNo = 1;

            InputStream in;
            try {
                in = resource.openStream();
            } catch (IOException e) {
                throw new ParseException("Can't open resource", e);
            }

            // Read as much as we can
            try {
                InputStreamReader reader = new InputStreamReader(in, "utf-8");
                BufferedReader lineIn = new BufferedReader(reader);
                String line;
                while ((line = lineIn.readLine()) != null) {
                    contents.append(line);
                    lineNo++;
                }
            } catch (IOException e) {
                String errorMessage = String.format("\nCan't read class resource %s at line %d.", resource, lineNo);
                contents.append(errorMessage);
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }

            doc = contents.toString();
        }
        return doc;
    }

}
