package net.bodz.mda.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Predicate2;

public class AutoCompileLoader extends ClassLoader {

    private final JavaCompiler compiler;

    public AutoCompileLoader(JavaCompiler compiler) {
        super();
        this.compiler = compiler;
    }

    public AutoCompileLoader(JavaCompiler compiler, ClassLoader parent) {
        super(parent);
        this.compiler = compiler;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File src = compiler.getSourceFile(name);
        if (!src.isFile())
            throw new ClassNotFoundException();
        File dst = compiler.getClassFile(name);
        Files.make(dst, src, new Predicate2<File, File[]>() {
            @Override
            public boolean eval(File dst, File[] srcs) {
                for (File src : srcs)
                    try {
                        compiler.compile(src);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                return true;
            }
        });
        if (!dst.exists())
            throw new RuntimeException(
                    "failed to compile: no class file generated");
        if (dst.lastModified() < src.lastModified())
            throw new RuntimeException("failed to compile: recompile failed");

        // load all pack.age.class[$inner]es
        Class<?> askedClass = null;
        final String dstName = Files.getName(dst);
        File dstdir = dst.getParentFile();
        String[] allsub = dstdir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String ext = Files.getExtension(name);
                if (!"class".equals(ext))
                    return false;
                return name.startsWith(dstName);
            }
        });
        for (String sub : allsub) {
            File subf = new File(dstdir, sub);
            String subname = Files
                    .getRelativeName(subf, compiler.getClassDir());
            // chop ".class"
            subname = subname.substring(0, subname.length() - 6);
            subname = subname.replace('\\', '/');
            subname = subname.replace('/', '.');
            try {
                byte[] bytes = Files.readBytes(subf);
                Class<?> clazz = defineClass(subname, bytes, 0, bytes.length);
                if (name.equals(subname))
                    askedClass = clazz;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        if (askedClass == null)
            throw new ClassNotFoundException(name);
        return askedClass;
    }

}
