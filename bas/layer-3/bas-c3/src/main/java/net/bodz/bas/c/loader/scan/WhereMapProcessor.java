package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class WhereMapProcessor
        extends AbstractFileOrEntryProcessor {

    Map<String, List<URL>> whereMap = new LinkedHashMap<String, List<URL>>();

    public List<URL> getResources(String resourceName) {
        List<URL> resources = whereMap.get(resourceName);
        if (resources == null) {
            resources = new ArrayList<URL>();
            whereMap.put(resourceName, resources);
        }
        return resources;
    }

    @Override
    public void process(File file, String resourceName) {
        URL resource = toResource(file);
        add(resourceName, resource);
    }

    @Override
    public void process(ZipFile file, ZipEntry entry) {
        URL resource = toResource(file, entry);
        add(entry.getName(), resource);
    }

    void add(String name, URL resource) {
        List<URL> list = getResources(name);
        list.add(resource);
    }

    public Map<String, List<URL>> getResourceMap() {
        return whereMap;
    }

    public Set<String> getResourceNames() {
        return whereMap.keySet();
    }

}
