package net.bodz.bas.a;

import static net.bodz.bas.types.util.ArrayOps.Ints;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.ClassLocal;
import net.bodz.bas.types.util.Annotations;
import net.bodz.bas.types.util.Ns;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.ui.a.Icon;

public class ClassInfo {

    private final Class<?>    clazz;
    private final ClassLoader loader;
    private boolean           loaded;

    private String            name;
    private String            doc;
    private URL               icon;
    private Map<String, URL>  iconMap;
    private String            author;
    private int[]             version;
    private String            dateString;

    public ClassInfo(Class<?> clazz) {
        assert clazz != null;
        this.clazz = clazz;
        this.loader = clazz.getClassLoader();
        load();
    }

    protected String join(String[] components) {
        if (components == null || components.length == 0)
            return null;
        return Strings.join(", ", components); //$NON-NLS-1$
    }

    protected void load() {
        if (loaded)
            return;
        name = A_bas.getDisplayName(clazz);
        doc = A_bas.getDoc(clazz);

        String[] iconDefs = Annotations._getValue(clazz, Icon.class);
        if (iconDefs != null)
            for (String iconDef : iconDefs) {
                int sep = iconDef.indexOf('|');
                if (sep == -1) {
                    assert this.icon == null : "default icon duplicate"; //$NON-NLS-1$
                    this.icon = loadIcon(iconDef);
                } else {
                    String usage = iconDef.substring(0, sep);
                    iconDef = iconDef.substring(sep + 1);
                    if (iconMap == null)
                        iconMap = new HashMap<String, URL>();
                    else
                        assert !iconMap.containsKey(usage) : "duplicate icon usage: " //$NON-NLS-1$
                                + usage;
                    iconMap.put(usage, loadIcon(iconDef));
                }
            }

        String[] author = Ns._getValue(clazz, Author.class);
        if (author != null)
            this.author = join(author);

        int[] version = Ns._getValue(clazz, Version.class);
        if (version != null)
            this.version = version;
    }

    protected URL loadIcon(String name) {
        URL url = loader.getResource(name);
        if (url == null)
            throw new IllegalArgumentException("res isn't existed: " + name); //$NON-NLS-1$
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public URL getIcon() {
        return icon;
    }

    public URL getIcon(String usage) {
        if (iconMap != null) {
            URL url = iconMap.get(usage);
            if (url != null)
                return url;
        }
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    public URL[] getIcons() {
        if (iconMap != null)
            return iconMap.values().toArray(new URL[0]);
        if (icon != null)
            return new URL[] { icon };
        return null;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int[] getVersion() {
        return version;
    }

    public void setVersion(int... version) {
        this.version = version;
    }

    public String getVersionString() {
        return getVersionString(true);
    }

    public String getVersionString(boolean includeRevNumber) {
        int[] ver = getVersion();
        if (!includeRevNumber)
            ver = Ints.copyOf(ver, ver.length - 1);
        return Strings.joinDot(ver);
    }

    public void setVersionString(String version) {
        String[] parts = version.split("\\."); //$NON-NLS-1$
        this.version = new int[parts.length];
        for (int i = 0; i < parts.length; i++)
            this.version[i] = Integer.parseInt(parts[i]);
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
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
