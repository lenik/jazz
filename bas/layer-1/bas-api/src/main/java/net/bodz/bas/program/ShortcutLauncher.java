package net.bodz.bas.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ShortcutLauncher {

    protected String targetClassName;

    protected File userDir; // cwd
    protected File appDir;
    boolean findParentOfLibDirForApp = true;

    File launcherDir;
    protected URL launcherURL;
    boolean mavenSupport = true;
    boolean includeLauncherAlso = true;

    boolean autoSelectSwtVersion = true;

    // search in [ appDir, userDir ] order.
    String[] classpathNames = { //
            "classpath.lst", //
            "classpath.release.lst", //
            "classpath.debug.lst", //
    };

    URL classpathURL;
    boolean isRelease;
    boolean isDebug;
    List<String> classpathLines;
    protected List<URL> URLs = new ArrayList<>();

    public ShortcutLauncher() {
        String launcherFqcn = getClass().getName();
        String suffix = "Launcher";
        String fqcn;
        if (launcherFqcn.endsWith(suffix))
            fqcn = launcherFqcn.substring(0, launcherFqcn.length() - suffix.length());
        else
            fqcn = beforeLastUpperCase(launcherFqcn);
        targetClassName = fqcn;

        userDir = new File(System.getProperty("user.dir"));
        findAppDir();
        if (appDir == null)
            throw new RuntimeException("Can't find application dir.");
    }

    protected ShortcutLauncher load()
            throws IOException {
        File classpathFile = findClasspathFile();
        if (classpathFile != null) {
            List<String> lines = readFileLines(classpathFile);

            debug("Load " + classpathFile);
            for (String path : lines) {
                File item = new File(appDir, path);
                if (item.exists()) {
                    URL itemURL = item.getCanonicalFile().toURI().toURL();
                    if (selectItem(path, item, itemURL)) {
                        debug("    Included: " + itemURL);
                        URLs.add(itemURL);
                    } else {
                        debug("Auto excluded incompatible SWT component: " + path);
                    }
                } else {
                    throw new RuntimeException("unexisted classpath item: " + path);
                }
            }
        }
        return this;
    }

    static final String osName = System.getProperty("os.name");
    static final int archBits;
    static {
        int bits = 32;

        String sunArchDataModel = System.getProperty("sun.arch.data.model");
        if (sunArchDataModel != null) {
            if ("64".equals(sunArchDataModel))
                bits = 64;
        } else {
            String osArch = System.getProperty("os.arch");
            if ("amd64".equals(osArch))
                bits = 64;
        }
        archBits = bits;
    }

    boolean selectItem(String line, File file, URL url) {
        if (autoSelectSwtVersion) {
            String name = file.getName();
            SwtDepInfo info = SwtDepInfo.parse(name);
            if (info != null) {
                if (osName.startsWith("Windows")) {
                    if (!"win32".equals(info.os))
                        return false;
                } else if (osName.startsWith("Linux")) {
                    if (!"linux".equals(info.os))
                        return false;
                }
                if (archBits != info.bits)
                    return false;
            }
        }
        return true;
    }

    static List<String> readFileLines(File file)
            throws IOException {
        try (FileInputStream fin = new FileInputStream(file)) {
            InputStreamReader isr = new InputStreamReader(fin, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;
                if (line.startsWith("#"))
                    continue;
                lines.add(line);
            }
            return lines;
        }
    }

    void findAppDir() {
        Class<?> probeClass = getClass();
        URL probeClassURL = probeClass.getResource(probeClass.getSimpleName() + ".class");
        if (probeClassURL == null)
            return;

        switch (probeClassURL.getProtocol()) {
        case "jar":
            String jarEntryPath = probeClassURL.getPath();
            int excl = jarEntryPath.lastIndexOf('!');
            String jarPath = jarEntryPath.substring("file:".length(), excl);
            File jarFile = new File(jarPath);
            launcherURL = toURL(jarFile);
            appDir = launcherDir = jarFile.getParentFile();

            if ("lib".equals(appDir.getName()))
                if (findParentOfLibDirForApp) {
                    File checkDir = appDir.getParentFile();
                    for (String classpathName : classpathNames)
                        if (new File(checkDir, classpathName).exists()) {
                            appDir = checkDir;
                            break;
                        }
                }
            break;

        case "file":
            String path = probeClassURL.getPath();
            int qLen = probeClass.getName().length() + "/.class".length();
            String dirPath = path.substring(0, path.length() - qLen);
            File dir = new File(dirPath);
            launcherDir = dir;
            launcherURL = toURL(launcherDir);

            if (mavenSupport) {
                if ("classes".equals(dir.getName())) {
                    File parent = dir.getParentFile();
                    if ("target".equals(parent.getName()))
                        dir = parent.getParentFile();
                }
            }
            appDir = dir;
            break;
        }
    }

    static URL toURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    File findClasspathFile() {
        List<File> searchDirs = new ArrayList<>();
        if (appDir != null)
            searchDirs.add(appDir);
        if (userDir != null)
            searchDirs.add(userDir);

        for (File searchDir : searchDirs) {
            for (String classpathName : classpathNames) {
                File classpathFile = new File(searchDir, classpathName);
                if (classpathFile.exists()) {
                    return classpathFile;
                }
            }
        }
        return null;
    }

    protected Class<?> resolveTargetClass()
            throws Exception {
        load();

        ClassLoader skelLoader = getClass().getClassLoader();
        ClassLoader parent = skelLoader.getParent();

        if (includeLauncherAlso) {
            if (!URLs.contains(launcherURL)) {
                debug("Include launcher also: " + launcherURL);
                URLs.add(0, launcherURL);
            }
        }

        URL[] array = URLs.toArray(new URL[0]);
        ClassLoader loader = new URLClassLoader(array, parent);

        Thread.currentThread().setContextClassLoader(loader);

        Class<?> targetClass = Class.forName(targetClassName, true, loader);
        return targetClass;
    }

    public void execute(String... args)
            throws Exception {
        Class<?> targetClass = resolveTargetClass();
        Method mainMethod = targetClass.getMethod("main", String[].class);
        mainMethod.invoke(null, (Object) args);
    }

    static int lastIndexOfUpperCase(String s) {
        int len = s.length();
        for (int pos = len - 1; pos >= 0; pos--) {
            char ch = s.charAt(pos);
            if (Character.isUpperCase(ch))
                return pos;
        }
        return -1;
    }

    static String beforeLastUpperCase(String s) {
        int pos = lastIndexOfUpperCase(s);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    static boolean showDebugMessages;
    static {
        showDebugMessages = "1".equals(System.getenv("DEBUG"));
    }

    static void debug(String message) {
        if (showDebugMessages)
            System.err.println(message);
    }

    public static void main(String[] args)
            throws Throwable {
        new ShortcutLauncher().execute(args);
    }

}

class SwtDepInfo {

    String platform;
    String os;
    int bits;

    public static SwtDepInfo parse(String name) {
        String prefix = "org.eclipse.swt.";
        if (name.startsWith(prefix))
            name = name.substring(prefix.length());
        else
            return null;

        if (name.endsWith(".jar"))
            name = name.substring(0, name.length() - 4);

        int dash = name.lastIndexOf('-');
        if (dash != -1)
            name = name.substring(0, dash); // remove version

        String[] words = name.split("\\.");
        SwtDepInfo info = new SwtDepInfo();
        info.platform = words[0];
        info.os = words[1];

        String arch = words[2];
        switch (arch) {
        case "x86_64":
            info.bits = 64;
            break;
        case "x86":
        default:
            info.bits = 32;
            break;
        }
        return info;
    }

}