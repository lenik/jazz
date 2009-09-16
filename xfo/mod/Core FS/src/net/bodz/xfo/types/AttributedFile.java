package net.bodz.xfo.types;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.bodz.bas.lang.err.ParseException;

public abstract class AttributedFile {

    private final File                file;

    private List<VersionedElement<?>> references;
    private Object[]                  cacheVersion;
    private AttributedElement         cachedAttributes;

    public AttributedFile(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
        this.references = new ArrayList<VersionedElement<?>>();
        this.references.add(new FileVersion(file));
    }

    public File getFile() {
        return file;
    }

    protected void addReference(VersionedElement<?> versionedElement) {
        if (versionedElement == null)
            throw new NullPointerException("versionedElement");
        references.add(versionedElement);
    }

    private Object[] getVersion() {
        int n = references.size();
        Object[] vers = new Object[n];
        for (int i = 0; i < n; i++) {
            Object ver = references.get(i).getVersion();
            vers[i] = ver;
        }
        return vers;
    }

    synchronized boolean isCacheExpired() {
        Object[] current = getVersion();
        if (cacheVersion != null) {
            assert cacheVersion.length == current.length;
            for (int i = 0; i < current.length; i++) {
                @SuppressWarnings("unchecked")
                Comparator<Object> comparator = (Comparator<Object>) references.get(i)
                        .getComparator();
                Object cache = cacheVersion[i];
                Object cur = current[i];
                if (comparator.compare(cache, cur) < 0) { // cur is newer than cache.
                    cacheVersion = current;
                    return true;
                }
            }
            return false;
        }
        cacheVersion = current;
        return true;
    }

    public AttributedElement getAttributes() throws IOException, ParseException {
        if (isCacheExpired())
            cachedAttributes = fetchAttributes();
        return cachedAttributes;
    }

    protected abstract AttributedElement fetchAttributes() throws IOException, ParseException;

}
