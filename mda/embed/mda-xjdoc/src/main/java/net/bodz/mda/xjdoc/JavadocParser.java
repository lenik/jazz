package net.bodz.mda.xjdoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;

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
        File sourceFile = getSourceFile(Example.class);
        JavaDocBuilder builder = new JavaDocBuilder();

        // ClassLibrary lib = builder.getClassLibrary();

        builder.addSourceTree(sourceFile.getParentFile());
        JavaClass jclass = builder.getClassByName(Example.class.getName());
        DocletTag[] tags = jclass.getTags();
        for (JavaField field : jclass.getFields()) {
            DocletTag[] fieldTags = field.getTags();
            System.out.println(fieldTags);
        }
        System.out.println("Done");
    }

}
