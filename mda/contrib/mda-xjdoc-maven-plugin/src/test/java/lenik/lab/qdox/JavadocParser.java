package lenik.lab.qdox;

import java.io.File;
import java.io.FileNotFoundException;

import net.bodz.bas.c.type.ClassResource;

import com.thoughtworks.qdox.JavaProjectBuilder;

import user.ExampleClass;

public class JavadocParser {

    public static File getSourceFile(Class<?> clazz)
            throws FileNotFoundException {
        File classFile = ClassResource.getClassBytesFile(clazz);

        String path = classFile.getPath();
        path = path.replace("/target/classes/", "/src/main/java/");
        path = path.replace("/target/test-classes/", "/src/test/java/");
        path = path.replace(".class", ".java");

        File sourceFile = new File(path);
        if (!sourceFile.exists())
            throw new FileNotFoundException(path);

        return sourceFile;
    }

    public static void main(String[] args)
            throws FileNotFoundException {
        File sourceFile = getSourceFile(ExampleClass.class);
        JavaProjectBuilder builder = new JavaProjectBuilder();

        // ClassLibrary lib = builder.getClassLibrary();
        builder.addSourceTree(sourceFile.getParentFile());
    }

}
