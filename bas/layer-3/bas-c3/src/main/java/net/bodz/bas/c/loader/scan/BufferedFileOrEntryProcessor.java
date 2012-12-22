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

public class BufferedFileOrEntryProcessor
        extends AbstractFileOrEntryProcessor {

    Map<String, List<URL>> resourceMap = new LinkedHashMap<String, List<URL>>();

    public List<URL> getResources(String resourceName) {
        List<URL> resources = resourceMap.get(resourceName);
        if (resources == null) {
            resources = new ArrayList<URL>();
            resourceMap.put(resourceName, resources);
        }
        return resources;
    }

    @Override
    public void process(File file, String resourceName) {
        List<URL> resources = getResources(resourceName);
        URL resource = toResource(file);
        resources.add(resource);
    }

    @Override
    public void process(ZipFile file, ZipEntry entry) {
        List<URL> resources = getResources(entry.getName());
        URL resource = toResource(file, entry);
        resources.add(resource);
    }

    public Map<String, List<URL>> getResourceMap() {
        return resourceMap;
    }

    public Set<String> getResourceNames() {
        return resourceMap.keySet();
    }

}
