package net.bodz.bas.program;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.meta.build.ProgramName;

@ProgramName("progexec")
public class ProgramLauncher {

    public static void main(String[] args)
            throws Throwable {
        if (args.length < 1)
            throw new IllegalArgumentException("Expected program classname.");
        String prog = args[0];

        Class<?> mainClass = null;
        Method mainMethod;
        try {
            mainClass = Class.forName(prog);
        } catch (ClassNotFoundException e) {
            // continue to find.
        }
        if (mainClass == null)
            mainClass = resolveProgram(prog);

        if (mainClass == null) {
            System.err.println("Typedef-Map: ");
            for (ClassLoader ldr : loaders.keySet()) {
                NsSymMap nsmap = loaders.get(ldr);
                System.err.println("    # loaderX: " + ldr);
                ClassLoaderDumper dumper = new ClassLoaderDumper();
                dumper.dump(System.err, ldr, "        ");
                nsmap.dump(System.err, "    ");
                System.err.println();
            }
            throw new IllegalArgumentException("Program is unknown: " + prog);
        }

        try {
            mainMethod = mainClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            throw new Error(e.getMessage(), e);
        }

        args = Arrays.copyOfRange(args, 1, args.length);
        try {
            mainMethod.invoke(null, (Object) args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    static String PROGRAMS_RESOURCE = "META-INF/programs";
    static Map<ClassLoader, NsSymMap> loaders = new HashMap<>();
    static String NS_MAIN = "main";

    public static ClassLoader getDefaultLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        return loader;
    }

    public static Map<ClassLoader, NsSymMap> getAllTypedefMaps() {
        return loaders;
    }

    public static Class<?> resolveProgram(String prog)
            throws IOException {
        ClassLoader loader = getDefaultLoader();
        return resolveProgram(prog, loader);
    }

    public static Class<?> resolveProgram(String prog, ClassLoader loader)
            throws IOException {
        NsSymMap nsmap = loaders.get(loader);
        if (nsmap == null) {
            nsmap = new NsSymMap(loader, PROGRAMS_RESOURCE);
            nsmap.load();
            loaders.put(loader, nsmap);
        }

        try {
            return nsmap.resolve(NS_MAIN, prog, loader);
        } catch (ClassNotFoundException e) {
            String fqcn = nsmap.getText(NS_MAIN, prog);
            throw new Error(String.format("No class %s for program %s.", fqcn, prog), e);
        }
    }

}
