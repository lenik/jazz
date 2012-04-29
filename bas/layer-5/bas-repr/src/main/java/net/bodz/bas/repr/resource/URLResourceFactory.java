package net.bodz.bas.repr.resource;

import java.net.MalformedURLException;
import java.net.URL;

public class URLResourceFactory
        extends ResourceFactory {

    @Override
    public String getType() {
        return ResourceTypes.RT_URL;
    }

    @Override
    public IResource resolve(String path) {
        URL url;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            return null;
        }
        return new URLResource(url);
    }

}
