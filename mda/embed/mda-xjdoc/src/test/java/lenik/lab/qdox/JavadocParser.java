package lenik.lab.qdox;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import user.ExampleClass;


import com.thoughtworks.qdox.JavaDocBuilder;

public class JavadocParser {

    public static File getSourceFile(Class<?> clazz)
            throws FileNotFoundException {
        String classResourceName = clazz.getSimpleName() + ".class";
        URL classResource = clazz.getResource(classResourceName);
        File classFile;
        try {
            classFile = new File(classResource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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
        JavaDocBuilder builder = new JavaDocBuilder();

        // ClassLibrary lib = builder.getClassLibrary();
        builder.addSourceTree(sourceFile.getParentFile());
    }

}
