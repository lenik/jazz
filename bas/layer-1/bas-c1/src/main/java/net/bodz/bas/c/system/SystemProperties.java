package net.bodz.bas.c.system;

public class SystemProperties {

    public static boolean developMode;
    static {
        String devmode = System.getenv("DEVELOP_MODE");
        if (devmode == null)
            devmode = System.getProperty("DEVELOP_MODE");
        if ("1".equals(devmode))
            developMode = true;
    }

    public static boolean isDevelopMode() {
        return developMode;
    }

    public static final boolean javaVersion4OrAbove;
    public static final boolean javaVersion5OrAbove;
    public static final boolean javaVersion6OrAbove;
    public static final boolean javaVersion7OrAbove;
    static {
        String javaVersion = getJavaVersion();
        javaVersion4OrAbove = javaVersion.compareTo("1.4.0") >= 0;
        javaVersion5OrAbove = javaVersion.compareTo("1.5.0") >= 0;
        javaVersion6OrAbove = javaVersion.compareTo("1.6.0") >= 0;
        javaVersion7OrAbove = javaVersion.compareTo("1.7.0") >= 0;
    }

    // ________________________________________________________________________
    // ⇱ Part: Partial Generated Methods
    //

    /** awt.toolkit = sun.awt.windows.WToolkit */
    public static String getAwtToolkit() {
        return System.getProperty("awt.toolkit");
    }

    /** file.encoding = UTF-8 */
    public static String getFileEncoding() {
        return System.getProperty("file.encoding");
    }

    /** file.encoding.pkg = sun.io */
    public static String getFileEncodingPkg() {
        return System.getProperty("file.encoding.pkg");
    }

    /**
     * Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
     */
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    /** java.awt.graphicsenv = sun.awt.Win32GraphicsEnvironment */
    public static String getJavaAwtGraphicsenv() {
        return System.getProperty("java.awt.graphicsenv");
    }

    /** java.awt.printerjob = sun.awt.windows.WPrinterJob */
    public static String getJavaAwtPrinterjob() {
        return System.getProperty("java.awt.printerjob");
    }

    /**
     * Path used to find directories and JAR archives containing class files. Elements of the class
     * path are separated by a platform-specific character specified in the path.separator property.
     */
    public static String getJavaClassPath() {
        return System.getProperty("java.class.path");
    }

    /** java.class.version = 51.0 */
    public static String getJavaClassVersion() {
        return System.getProperty("java.class.version");
    }

    /**
     * java.endorsed.dirs = C:/lam/kala/abc.d/j/jdk-1.7.0/jre/lib/endorsed
     */
    public static String getJavaEndorsedDirs() {
        return System.getProperty("java.endorsed.dirs");
    }

    /**
     * java.ext.dirs = C:/lam/kala/abc.d/j/jdk-1.7.0/jre/lib/ext;C:/WINDOWS /Sun/Java/lib/ext
     */
    public static String getJavaExtDirs() {
        return System.getProperty("java.ext.dirs");
    }

    /**
     * Installation directory for Java Runtime Environment (JRE)
     */
    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    /** java.io.tmpdir = C:/Profiles/Shecti/Temp/ */
    public static String getJavaIoTmpdir() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * java.library.path = ...
     */
    public static String getJavaLibraryPath() {
        return System.getProperty("java.library.path");
    }

    /** java.runtime.name = Java(TM) SE Runtime Environment */
    public static String getJavaRuntimeName() {
        return System.getProperty("java.runtime.name");
    }

    /** java.runtime.version = 1.7.0-ea-b53 */
    public static String getJavaRuntimeVersion() {
        return System.getProperty("java.runtime.version");
    }

    /** java.specification.name = Java Platform API Specification */
    public static String getJavaSpecificationName() {
        return System.getProperty("java.specification.name");
    }

    /** java.specification.vendor = Sun Microsystems Inc. */
    public static String getJavaSpecificationVendor() {
        return System.getProperty("java.specification.vendor");
    }

    /** java.specification.version = 1.7 */
    public static String getJavaSpecificationVersion() {
        return System.getProperty("java.specification.version");
    }

    /**
     * JRE vendor name
     */
    public static String getJavaVendor() {
        return System.getProperty("java.vendor");
    }

    /** JRE vendor URL */
    public static String getJavaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    /** java.vendor.url.bug = http://java.sun.com/cgi-bin/bugreport.cgi */
    public static String getJavaVendorUrlBug() {
        return System.getProperty("java.vendor.url.bug");
    }

    /**
     * JRE version number
     */
    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    /** java.vm.info = mixed mode, sharing */
    public static String getJavaVmInfo() {
        return System.getProperty("java.vm.info");
    }

    /** java.vm.name = Java HotSpot(TM) Client VM */
    public static String getJavaVmName() {
        return System.getProperty("java.vm.name");
    }

    /** java.vm.specification.name = Java Virtual Machine Specification */
    public static String getJavaVmSpecificationName() {
        return System.getProperty("java.vm.specification.name");
    }

    /** java.vm.specification.vendor = Sun Microsystems Inc. */
    public static String getJavaVmSpecificationVendor() {
        return System.getProperty("java.vm.specification.vendor");
    }

    /** java.vm.specification.version = 1.0 */
    public static String getJavaVmSpecificationVersion() {
        return System.getProperty("java.vm.specification.version");
    }

    /** java.vm.vendor = Sun Microsystems Inc. */
    public static String getJavaVmVendor() {
        return System.getProperty("java.vm.vendor");
    }

    /** java.vm.version = 15.0-b04 */
    public static String getJavaVmVersion() {
        return System.getProperty("java.vm.version");
    }

    /**
     * Sequence used by operating system to separate lines in text files
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * Operating system architecture
     */
    public static String getOsArch() {
        return System.getProperty("os.arch");
    }

    /** Operating system name */
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    /**
     * Operating system version
     */
    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    /**
     * Path separator character used in java.class.path
     */
    public static String getPathSeparator() {
        return System.getProperty("path.separator");
    }

    /** sun.arch.data.model = 32 */
    public static String getSunArchDataModel() {
        return System.getProperty("sun.arch.data.model");
    }

    /**
     * sun.boot.class.path = C:/lam/kala/abc.d/j/jdk-1.7.0/jre/lib/resources .jar;C:/lam/kala\
     * \abc.d/j/jdk-1.7.0/jre/lib/rt.jar;C:/lam/kala/abc .d/j/jdk-1.7.0/
     * jre/lib/sunrsasign.jar;C:/lam/kala/abc.d/j/jdk-1.7 .0/jre/lib/jsse
     * .jar;C:/lam/kala/abc.d/j/jdk-1.7.0/jre/lib/jce.jar ;C:/lam/kala/abc
     * .d/j/jdk-1.7.0/jre/lib/charsets.jar;C:/lam/kala/ abc.d/j/jdk-1.7.0/jre/classes
     */
    public static String getSunBootClassPath() {
        return System.getProperty("sun.boot.class.path");
    }

    /** sun.boot.library.path = C:/lam/kala/abc.d/j/jdk-1.7.0/jre/bin */
    public static String getSunBootLibraryPath() {
        return System.getProperty("sun.boot.library.path");
    }

    /** sun.cpu.endian = little */
    public static String getSunCpuEndian() {
        return System.getProperty("sun.cpu.endian");
    }

    /** sun.cpu.isalist = */
    public static String getSunCpuIsalist() {
        return System.getProperty("sun.cpu.isalist");
    }

    /** sun.desktop = windows */
    public static String getSunDesktop() {
        return System.getProperty("sun.desktop");
    }

    /** sun.io.unicode.encoding = UnicodeLittle */
    public static String getSunIoUnicodeEncoding() {
        return System.getProperty("sun.io.unicode.encoding");
    }

    /** sun.java.launcher = SUN_STANDARD */
    public static String getSunJavaLauncher() {
        return System.getProperty("sun.java.launcher");
    }

    /** sun.jnu.encoding = GBK */
    public static String getSunJnuEncoding() {
        return System.getProperty("sun.jnu.encoding");
    }

    /** sun.management.compiler = HotSpot Client Compiler */
    public static String getSunManagementCompiler() {
        return System.getProperty("sun.management.compiler");
    }

    /** sun.os.patch.level = Service Pack 3, v.5755 */
    public static String getSunOsPatchLevel() {
        return System.getProperty("sun.os.patch.level");
    }

    /**
     * User country.
     * 
     * user.country = CN
     */
    public static String getUserCountry() {
        return System.getProperty("user.country");
    }

    /** User working directory */
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    /** User home directory */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /** user.language = zh */
    public static String getUserLanguage() {
        return System.getProperty("user.language");
    }

    /** User account name */
    public static String getUserName() {
        return System.getProperty("user.name");
    }

    /** User time zone */
    public static String getUserTimezone() {
        return System.getProperty("user.timezone");
    }

    /** user.variant = */
    public static String getUserVariant() {
        return System.getProperty("user.variant");
    }

}
