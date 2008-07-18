package net.bodz.bas.annotations;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.ClassLocal;
import net.bodz.bas.types.util.Annotations;
import net.bodz.bas.types.util.Strings;

public class ClassInfo {

    private final Class<?>    clazz;
    private final ClassLoader loader;
    private boolean           loaded;

    private String            doc;
    private URL               icon;
    private Map<String, URL>  iconMap;
    private String            author;
    private int[]             version;

    public ClassInfo(Class<?> clazz) {
        assert clazz != null;
        this.clazz = clazz;
        this.loader = clazz.getClassLoader();
    }

    protected String join(String[] components) {
        if (components == null || components.length == 0)
            return null;
        return Strings.join(", ", components);
    }

    protected void load() {
        if (loaded)
            return;
        String[] doc = Annotations.getAnnotation(clazz, Doc.class);
        if (doc != null)
            this.doc = join(doc);

        String[] icon = Annotations.getAnnotation(clazz, Icon.class);
        if (icon != null)
            for (String iconDef : icon) {
                int sep = iconDef.indexOf('|');
                if (sep == -1) {
                    assert this.icon == null : "default icon duplicate";
                    this.icon = loadIcon(iconDef);
                } else {
                    String usage = iconDef.substring(0, sep);
                    iconDef = iconDef.substring(sep + 1);
                    if (iconMap == null)
                        iconMap = new HashMap<String, URL>();
                    else
                        assert !iconMap.containsKey(usage) : "duplicate icon usage: "
                                + usage;
                    iconMap.put(usage, loadIcon(iconDef));
                }
            }

        String[] author = Annotations.getAnnotation(clazz, Author.class);
        if (author != null)
            this.author = join(author);

        int[] version = Annotations.getAnnotation(clazz, Version.class);
        if (version != null)
            this.version = version;
    }

    protected URL loadIcon(String name) {
        URL url = loader.getResource(name);
        return url;
    }

    public String getDoc() {
        load();
        return doc;
    }

    public URL getIcon() {
        load();
        return icon;
    }

    public URL getIcon(String usage) {
        load();
        if (iconMap != null) {
            URL url = iconMap.get(usage);
            if (url != null)
                return url;
        }
        return icon;
    }

    public String getAuthor() {
        load();
        return author;
    }

    public int[] getVersion() {
        load();
        return version;
    }

    public String getVersionString() {
        return Strings.joinDot(getVersion());
    }

    private static final ClassLocal<ClassInfo> infos;
    static {
        // Weak ClassLocal
        infos = new ClassLocal<ClassInfo>();
    }

    public static ClassInfo get(Class<?> clazz) {
        ClassInfo info = infos.get(clazz);
        if (info == null)
            infos.put(clazz, info = new ClassInfo(clazz));
        return info;
    }

}
