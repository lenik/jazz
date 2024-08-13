package net.bodz.bas.io.res;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ContainerItemList
        implements
            Iterable<IContainerItem> {

    List<IContainerItem> list = new ArrayList<>();
    Map<String, List<IContainerItem>> pathMap = new HashMap<>();

    @Override
    public Iterator<IContainerItem> iterator() {
        return list.iterator();
    }

    public Set<String> paths() {
        return pathMap.keySet();
    }

    List<IContainerItem> forPath(String path) {
        List<IContainerItem> list = pathMap.get(path);
        if (list == null) {
            list = new ArrayList<>();
            pathMap.put(path, list);
        }
        return list;
    }

    public void addItem(IContainerItem item) {
        list.add(item);
        String itemPath = item.getItemPath();
        forPath(itemPath).add(item);
    }

    public void addDirSubpath(File file, String relativePath) {
        addItem(new DirSubpathItem(file, relativePath));
    }

//    public void addZipEntry(ZipFile zipFile, ZipEntry entry) {
//        addItem(new ZipEntryItem(zipFile, entry));
//    }

    public void addJarEntry(JarFile jarFile, JarEntry entry) {
        addItem(new JarEntryItem(jarFile, entry));
    }

}
