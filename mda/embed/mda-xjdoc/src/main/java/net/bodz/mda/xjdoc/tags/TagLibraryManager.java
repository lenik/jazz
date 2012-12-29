package net.bodz.mda.xjdoc.tags;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.err.DuplicatedKeyException;

public class TagLibraryManager {

    static Map<String, ITagLibrary> taglibMap;

    static {
        taglibMap = new HashMap<String, ITagLibrary>();
        taglibMap.put("javadoc", new JavadocTagLibrary());
    }

    public static void register(String name, ITagLibrary taglib) {
        if (name == null)
            throw new NullPointerException("name");
        if (taglib == null)
            throw new NullPointerException("taglib");

        ITagLibrary existing = taglibMap.get(name);
        if (existing != null)
            throw new DuplicatedKeyException("Taglib name " + name + " is already used.");

        taglibMap.put(name, taglib);
    }

    public static ITagLibrary resolve(String name) {
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

    public static String nameOf(ITagLibrary taglib) {
        if (taglib == null)
            throw new NullPointerException("taglib");
        for (Entry<String, ITagLibrary> entry : taglibMap.entrySet()) {
            if (entry.getValue() == taglib)
                return entry.getKey();
        }
        return null;
    }

    public static TagLibrarySet parseSet(String taglibNames) {
        if (taglibNames == null)
            throw new NullPointerException("taglibNames");

        TagLibrarySet set = new TagLibrarySet();

        for (String taglibName : taglibNames.split(",")) {
            taglibName = taglibName.trim();
            if (taglibName.isEmpty())
                continue;

            ITagLibrary taglib = TagLibraryManager.resolve(taglibName);
            if (taglib == null)
                throw new IllegalArgumentException("Undefined taglib: " + taglibName);

            set.add(taglib);
        }
        return set;
    }

}
