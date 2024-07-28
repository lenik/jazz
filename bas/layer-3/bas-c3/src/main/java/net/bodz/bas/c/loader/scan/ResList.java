package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResList
        implements
            Iterable<IResourceItem> {

    List<IResourceItem> list = new ArrayList<>();
    Map<String, List<IResourceItem>> pathMap = new HashMap<>();

    @Override
    public Iterator<IResourceItem> iterator() {
        return list.iterator();
    }

    public Set<String> paths() {
        return pathMap.keySet();
    }

    List<IResourceItem> forPath(String path) {
        List<IResourceItem> list = pathMap.get(path);
        if (list == null) {
            list = new ArrayList<>();
            pathMap.put(path, list);
        }
        return list;
    }

    public void addItem(IResourceItem item) {
        list.add(item);
        String relativePath = item.getRelativePath();
        forPath(relativePath).add(item);
    }

    public void addFileItem(File file, String relativePath) {
        addItem(new FileItem(file, relativePath));
    }

    public void addJarItem(ZipFile zipFile, ZipEntry entry) {
        addItem(new JarItem(zipFile, entry));
    }

}
