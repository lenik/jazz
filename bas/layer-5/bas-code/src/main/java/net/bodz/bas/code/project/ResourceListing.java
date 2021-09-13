package net.bodz.bas.code.project;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.io.res.builtin.URLResource;

public class ResourceListing {

    Charset listingCharset = Charsets.UTF8;
    boolean skipCommentLines = true;
    boolean skipDirNames = true;
    boolean hasComments = false;
    URL listingUrl;

    public ResourceListing(URL listingUrl) {
        if (listingUrl == null)
            throw new NullPointerException("listingUrl");
        this.listingUrl = listingUrl;
    }

    /**
     * @return <code>false</code> if canceled.
     */
    public boolean find(IResourceCallback callback) {
        URLResource res = new URLResource(listingUrl, listingCharset);
        for (String name : res.read().lines()) {
            if (hasComments) {
                int sharp = name.indexOf('#');
                if (sharp != -1)
                    name = name.substring(0, sharp);
            }

            name = name.trim();

            while (name.startsWith("./"))
                name = name.substring(2);

            if (name.isEmpty())
                continue;

            if (skipCommentLines)
                if (name.startsWith("#"))
                    continue;

            if (skipDirNames) {
                if (name.equals("."))
                    continue;
                if (name.endsWith("/"))
                    continue;
            }

            try {
                URL url = new URL(listingUrl, name);
                if (!callback.resource(name, url))
                    return false;
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("malformed name: " + name, e);
            }
        }
        return true;
    }

}
