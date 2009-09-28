package net.bodz.inplas.kid;

import java.util.HashSet;
import java.util.Set;

public class JavaUtil {

    private static String[]    javaKeywords1 = new String[] { "abstract",
            "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float", "for", "goto",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws", "transient",
            "try", "void", "volatile", "while", };

    private static Set<String> javaKeywords;
    static {
        javaKeywords = new HashSet<String>();
        for (String keyword : javaKeywords1)
            javaKeywords.add(keyword);
    }

    public static String getJavaName(String name) {
        if (javaKeywords.contains(name))
            return name + "_";
        return name;
    }

}
