package net.bodz.mda.xjdoc.taglib;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.DuplicatedKeyException;

public class TagLibraryLoader {

    static final Logger logger = LoggerFactory.getLogger(TagLibraryLoader.class);

    private Map<String, ITagLibrary> taglibMap;

    public TagLibraryLoader(ClassLoader loader) {
        taglibMap = new HashMap<String, ITagLibrary>();

        logger.info("Search taglibs in class loader:");
        for (ITagLibrary taglib : ServiceLoader.load(ITagLibrary.class, loader)) {
            String name = taglib.getName();
            logger.info("    Found taglib " + name + " = " + ObjectInfo.getSimpleId(taglib));
            register(name, taglib);
        }
    }

    public Map<String, ITagLibrary> getTaglibMap() {
        return Collections.unmodifiableMap(taglibMap);
    }

    public void register(String name, ITagLibrary taglib) {
        if (name == null)
            throw new NullPointerException("name");
        if (taglib == null)
            throw new NullPointerException("taglib");

        ITagLibrary existing = taglibMap.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Taglib name " + name + " is already used.");

        taglibMap.put(name, taglib);
    }

    public ITagLibrary resolve(String name) {
        ITagLibrary taglib = taglibMap.get(name);
        if (taglib == null)
            try {
                Class<? extends ITagLibrary> taglibClass = (Class<? extends ITagLibrary>) Class.forName(name);
                taglib = taglibClass.newInstance();
            } catch (ClassNotFoundException e) {
                return null;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to instantiate taglib: " + name, e);
            }
        return taglib;
    }

    public String nameOf(ITagLibrary taglib) {
        if (taglib == null)
            throw new NullPointerException("taglib");
        for (Entry<String, ITagLibrary> entry : taglibMap.entrySet()) {
            if (entry.getValue() == taglib)
                return entry.getKey();
        }
        return null;
    }

    public TagLibrarySet parseSet(String taglibNames) {
        if (taglibNames == null)
            throw new NullPointerException("taglibNames");

        if ("*".equals(taglibNames)) {
            Collection<ITagLibrary> taglibs = taglibMap.values();
            return new TagLibrarySet(taglibs);
        }

        TagLibrarySet set = new TagLibrarySet();

        for (String taglibName : taglibNames.split(",")) {
            taglibName = taglibName.trim();
            if (taglibName.isEmpty())
                continue;

            ITagLibrary taglib = resolve(taglibName);
            if (taglib == null)
                throw new IllegalArgumentException("Undefined taglib: " + taglibName);

            set.add(taglib);
        }
        return set;
    }

}
