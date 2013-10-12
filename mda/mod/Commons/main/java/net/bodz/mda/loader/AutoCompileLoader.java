package net.bodz.mda.loader;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Pred2;

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
        Files.make(dst, src, new Pred2<File, File[]>() {
            @Override
            public boolean test(File dst, File[] srcs) {
                for (File src : srcs)
                    try {
                        compiler.compile(src);
                    } catch (IOException e) {
                        throw new CompilerError(e.getMessage(), e);
                    }
                return true;
            }
        });
        if (!dst.exists())
            throw new CompilerError(
                    "failed to compile: no class file generated");
        if (dst.lastModified() < src.lastModified())
            throw new CompilerError("failed to compile: recompile failed");

        byte[] bytes;
        try {
            bytes = Files.readBytes(dst);
        } catch (IOException e) {
            throw new CompilerError(e.getMessage(), e);
        }
        Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
        return clazz;
    }

}
