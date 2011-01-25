package net.bodz.bas.meta.build;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.collection.util.ClassLocal;
import net.bodz.bas.meta.info.Author;
import net.bodz.bas.meta.info.DisplayNameUtil;
import net.bodz.bas.meta.info.DocUtil;
import net.bodz.bas.meta.info.SiteLink;
import net.bodz.bas.string.StringArray;
import net.bodz.bas.ui.a.Icon;
import net.bodz.bas.util.Nullables;

public class ClassInfo {

    private final Class<?> clazz;
    private final ClassLoader loader;
    private boolean loaded;

    private String name;
    private String doc;
    private URL icon;
    private Map<String, URL> iconMap;
    private String author;
    private String webSite;
    private int[] version;
    private String dateString;

    public ClassInfo(Class<?> clazz) {
        assert clazz != null;
        this.clazz = clazz;
        this.loader = clazz.getClassLoader();
        load();
    }

    protected String join(String[] components) {
        if (components == null || components.length == 0)
            return null;
        return StringArray.join(", ", components);
    }

    protected void load() {
        if (loaded)
            return;
        name = DisplayNameUtil.getDisplayName(clazz);
        doc = DocUtil.getDoc(clazz);

        String[] iconDefs = Nullables.getAnnotation(clazz, Icon.class).value();
        if (iconDefs != null)
            for (String iconDef : iconDefs) {
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
                        assert !iconMap.containsKey(usage) : "duplicate icon usage: " + usage;
                    iconMap.put(usage, loadIcon(iconDef));
                }
            }

        String[] author = Nullables.getAnnotation(clazz, Author.class).value();
        if (author != null)
            this.author = join(author);

        String[] webSites = Nullables.getAnnotation(clazz, SiteLink.class).value();
        if (webSites != null && webSites.length != 0)
            this.webSite = webSites[0];

        int[] version = Nullables.getAnnotation(clazz, Version.class).value();
        if (version != null)
            this.version = version;
    }

    protected URL loadIcon(String name) {
        URL url = loader.getResource(name);
        if (url == null)
            throw new IllegalArgumentException("res isn't existed: " + name);
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
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
            ver = Arrays.copyOf(ver, ver.length - 1);
        return StringArray.joinDot(ver);
    }

    public void setVersionString(String version) {
        String[] parts = version.split("\\.");
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
