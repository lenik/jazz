package net.bodz.bas.code.project;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;

public class ResourceMap
        extends HashMap<String, IStreamInputSource>
        implements
            ICodeModule {

    private static final long serialVersionUID = 1L;

    @Override
    public void prepareFiles(Path projectDir)
            throws IOException {
        for (Entry<String, IStreamInputSource> entry : this.entrySet()) {
            String name = entry.getKey();
            IStreamInputSource source = entry.getValue();

            Path file = projectDir.resolve(name).toAbsolutePath().normalize();
            Path dir = file.getParent();

            if (Files.notExists(dir))
                if (!FileFn.mkdirs(dir))
                    throw new IOException("can't mkdir " + dir);

            if (source.isTextMode()) {
                String content = source.to(StreamReading.class).readString();
                ResFn.path(file).write().writeString(content);
            } else {
                byte[] content = source.to(StreamReading.class).read();
                ResFn.path(file).write().write(content);
            }
        }
    }

    public void loadFromListing(ResourceListing listing) {
        listing.find(new IResourceCallback() {
            @Override
            public boolean resource(String name, URL url) {
                URLResource resource = ResFn.url(url);
                put(name, resource);
                return true;
            }
        });
    }

    public void loadFromListing(Class<?> clazz, String dirName) {
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
        return fromListing(clazz, clazz.getSimpleName() + ".d");
    }

    public static ResourceMap fromListing(Class<?> clazz, String dirName) {
        ResourceMap map = new ResourceMap();
        map.loadFromListing(clazz, dirName);
        return map;
    }

}
