package net.bodz.bas.code.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;

public class ResourceMap
        extends HashMap<String, IStreamInputSource>
        implements
            ICodeModule {

    private static final long serialVersionUID = 1L;

    @Override
    public void prepareFiles(File projectDir)
            throws IOException {
        for (Entry<String, IStreamInputSource> entry : this.entrySet()) {
            String name = entry.getKey();
            IStreamInputSource source = entry.getValue();

            File file = new File(projectDir, name).getAbsoluteFile();
            File dir = file.getParentFile();

            if (!dir.exists())
                if (!dir.mkdirs())
                    throw new IOException("can't mkdir " + dir);

            if (source.isCharPreferred()) {
                String content = source.to(StreamReading.class).readString();
                new FileResource(file).write().writeString(content);
            } else {
                byte[] content = source.to(StreamReading.class).read();
                new FileResource(file).write().write(content);
            }
        }
    }

    public void loadFromListing(ResourceListing listing) {
        listing.find(new IResourceCallback() {
            @Override
            public boolean resource(String name, URL url) {
                URLResource resource = new URLResource(url);
                put(name, resource);
                return true;
            }
        });
    }

    public void loadFromListing(Class<?> clazz) {
        String dirName = clazz.getSimpleName() + ".d";
        String listingName = dirName + "/FILES.LST";
        URL listingUrl = clazz.getResource(listingName);
        if (listingUrl == null)
            throw new IllegalArgumentException("Listing isn't existed: " + listingName);
        ResourceListing listing = new ResourceListing(listingUrl);
        loadFromListing(listing);
    }

    public static ResourceMap fromListing(ResourceListing listing) {
        ResourceMap map = new ResourceMap();
        map.loadFromListing(listing);
        return map;
    }

    public static ResourceMap fromListing(Class<?> clazz) {
        ResourceMap map = new ResourceMap();
        map.loadFromListing(clazz);
        return map;
    }

}
