package net.bodz.mda.xjdoc.taglib;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.rtx.Injector;

public class TagLibraryLoader {

    static final Logger logger = LoggerFactory.getLogger(TagLibraryLoader.class);

    Map<Class<?>, Object> caches = new HashMap<>();
    Map<String, ITagLibrary> nameMap = new HashMap<String, ITagLibrary>();

    Injector injector = new Injector();

    public TagLibraryLoader(ClassLoader loader) {
        logger.trace("Search taglibs in class loader:");
        try {
            // for (ITagLibrary taglib : ServiceLoader.load(ITagLibrary.class, loader)) {
            Iterator<Class<? extends ITagLibrary>> libClasses = IndexedTypes.list(ITagLibrary.class, false).iterator();
            while (libClasses.hasNext()) {
                Class<? extends ITagLibrary> libClass = libClasses.next();
                // System.err.println("LibClass ----- " + libClass);
                ITagLibrary instance = (ITagLibrary) caches.get(libClass);
                if (instance == null) {
                    try {
                        instance = injector.instantiate(libClass);
                    } catch (ReflectiveOperationException e) {
                        logger.error("Error instantiate " + libClass);
                        continue;
                    }
                    caches.put(libClass, instance);
                }

                String name = instance.getName();
                register(name, instance);
            }
        } catch (Error e) {
            logger.error("Service configuration error", e);
        }
    }

    public Map<String, ITagLibrary> getTaglibMap() {
        return Collections.unmodifiableMap(nameMap);
    }

    public void register(String name, ITagLibrary instance) {
        if (name == null)
            throw new NullPointerException("name");
        if (instance == null)
            throw new NullPointerException("instance");

        ITagLibrary existing = nameMap.get(name);
        if (existing != null) {
            if (existing == instance)
                return;
            throw new DuplicatedKeyException(name, existing);
        }

        logger.trace("    Register taglib " + name + " = " + ObjectInfo.getSimpleId(instance));
        nameMap.put(name, instance);
    }

    public ITagLibrary resolve(String name) {
        ITagLibrary taglib = nameMap.get(name);
        if (taglib == null && name.contains(".")) {
            String qName = name;
            try {
                @SuppressWarnings("unchecked")
                Class<? extends ITagLibrary> taglibClass = (Class<? extends ITagLibrary>) Class.forName(qName);
                taglib = injector.instantiate(taglibClass);
            } catch (ClassNotFoundException e) {
                return null;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to instantiate taglib: " + name, e);
            }
        }
        return taglib;
    }

    public String nameOf(ITagLibrary taglib) {
        if (taglib == null)
            throw new NullPointerException("taglib");
        for (Entry<String, ITagLibrary> entry : nameMap.entrySet()) {
            if (entry.getValue() == taglib)
                return entry.getKey();
        }
        return null;
    }

    /**
     * @param taglibNames
     *            Tag library names, separated by comma(<code>,</code>).
     *
     *            Specify "<code>*</code>" for all available tag libraries in the classpath.
     */
    public TagLibrarySet parseSet(String taglibNames) {
        if (taglibNames == null)
            throw new NullPointerException("taglibNames");

        if ("*".equals(taglibNames)) {
            Collection<ITagLibrary> taglibs = nameMap.values();
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

    static Map<ClassLoader, TagLibraryLoader> classLoaderMap = new HashMap<ClassLoader, TagLibraryLoader>();

    public static TagLibraryLoader getInstance() {
        return getInstance(TagLibraryLoader.class.getClassLoader());
    }

    public static TagLibraryLoader getInstance(ClassLoader classLoader) {
        TagLibraryLoader instance = classLoaderMap.get(classLoader);
        if (instance == null) {
            instance = new TagLibraryLoader(classLoader);
            classLoaderMap.put(classLoader, instance);
        }
        return instance;
    }

    public static TagLibrarySet allFor(ClassLoader classLoader) {
        if (classLoader == null)
            throw new NullPointerException("classLoader");
        return getInstance(classLoader).parseSet("*");
    }

    public static TagLibrarySet allFor(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = clazz.getClassLoader();
            if (classLoader == null)
                throw new NullPointerException("resourceLoader");
        }
        return allFor(classLoader);
    }

}
