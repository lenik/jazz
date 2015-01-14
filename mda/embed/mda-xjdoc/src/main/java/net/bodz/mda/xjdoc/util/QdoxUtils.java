package net.bodz.mda.xjdoc.util;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

public class QdoxUtils {

    public static List<JavaClass> getAllNestedClasses(JavaSource source) {
        List<JavaClass> list = new ArrayList<>();
        for (JavaClass c : source.getClasses())
            scanNestedClasses(list, c);
        return list;
    }

    public static List<JavaClass> getAllNestedClasses(JavaClass c) {
        List<JavaClass> list = new ArrayList<>();
        scanNestedClasses(list, c);
        return list;
    }

    static void scanNestedClasses(List<JavaClass> list, JavaClass c) {
        list.add(c);
        for (JavaClass nested : c.getNestedClasses())
            scanNestedClasses(list, nested);
    }

}
