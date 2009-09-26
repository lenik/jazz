package net.bodz.xml.models.pdb;

/**
 * @test {@link DocTest}
 */
public class Doc {

    /**
     * @return <code>null</code> if <code>doc</code> is <code>null</code>
     */
    public static String wrap(String doc, int width) {
        if (doc == null)
            return null;
        if (width < 2)
            throw new IllegalArgumentException("width is illegal: " + width);
        int n = doc.length();
        StringBuffer buf = new StringBuffer(n + n / width);
        int i = 0;
        while (i < n) {
            if (i != 0)
                buf.append('\n');
            int w = Math.min(n - i, width);
            String line = doc.substring(i, i + w);
            buf.append(line);
            i += w;
        }
        return buf.toString();
    }

    /**
     * @return <code>null</code> if <code>doc</code> is <code>null</code>
     */
    public static String unwrap(String doc) {
        if (doc == null)
            return null;
        int ln = doc.indexOf('\n');
        if (ln == -1)
            return doc;
        StringBuffer buf = new StringBuffer(doc.length());
        while (ln != -1) {
            String line = doc.substring(0, ln);
            buf.append(line);
            doc = doc.substring(ln + 1);
            ln = doc.indexOf('\n');
        }
        buf.append(doc);
        return buf.toString();
    }

}
