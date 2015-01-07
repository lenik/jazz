package net.bodz.bas.repr.content;

import java.net.URL;

import net.bodz.bas.io.res.IStreamResource;

public class StreamResourceContent
        extends MutableContent {

    private IStreamResource resource;
    private String name;
    private URL url;

    public StreamResourceContent(IStreamResource resource) {
        if (resource == null)
            throw new NullPointerException("resource");
        this.resource = resource;
    }

    public IStreamResource getResource() {
        return resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
