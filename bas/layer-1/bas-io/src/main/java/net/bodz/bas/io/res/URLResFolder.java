package net.bodz.bas.io.res;

import java.io.IOException;
import java.net.URL;


public class URLResFolder implements ResFolder {

    private URL context;

    public URLResFolder(URL context) {
        if (context == null)
            throw new NullPointerException("context-url"); //$NON-NLS-1$
        this.context = context;
    }

    public URL getContext() {
        return context;
    }

    /**
     * @param path
     *            URL spec to the context URL
     * @throws IOException
     * @see {@link URL#URL(URL, String)}
     */
    @Override
    public URLResLink get(String path) throws IOException {
        URL url = new URL(context, path);
        URLResLink link = new URLResLink(url);
        return link;
    }

    @Override
    public int hashCode() {
        return 0x19cfdec1 ^ context.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof URLResFolder) {
            URLResFolder a = (URLResFolder) obj;
            return context.equals(a.context);
        }
        return false;
    }

    @Override
    public String toString() {
        return context.toString();
    }

}
